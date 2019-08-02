package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.model.AccountLockException;
import atm.model.BankAccount;
import atm.model.InsufficientFundsException;

/**
 * Tests to verify BankAccount correctness.
 * @author Chris Carson
 */
public class BankAccountTest {
	BankAccount ba;
	@Before
	public void SetUp() {
		ba = new BankAccount("Checking", "12345", "1111", new BigDecimal("200.00"));
	}

	@After
	public void TearDown() {
		ba = null;
	}
	
	@Test
	public void AccounWithdrawTest() {
		try {
			ba.withdraw(new BigDecimal("110.55"));	
			assertEquals("89.45", ba.getBalance().toString());
		}
		catch(Exception ex) {
			fail("Should not have thrown Exception!");
		}
	}
	
	@Test
	public void AccountDepositTest() {
		try {
			ba.deposit(new BigDecimal("150.00"));
			assertEquals("350.00", ba.getBalance().toString());
		}
		catch(Exception ex) {
			fail("Should not have thrown Exception!");
		}
	}

	@Test
	public void AccountWithdrawAccountLockedTest() {
		try {
			ba.setAccountLock(true);
			ba.withdraw(new BigDecimal("50.00"));
			fail("Should have thrown AccountLockException");
		}
		catch (AccountLockException ex) {
			// Correct exception thrown
		}
		catch(Exception ex) {
			fail("Unexpected exception thrown!");
		}
	}
		
	@Test
	public void AccountDepositAccountLockedTest() {
		try {
			ba.setAccountLock(true);
			ba.deposit(new BigDecimal("150.00"));
			fail("Should have thrown AccountLockException");
		}
		catch (AccountLockException ex) {
			// Correct exception thrown
		}
		catch(Exception ex) {
			fail("Unexpected exception thrown!");
		}
	}
	
	@Test
	public void AccountWithdrawInsufficientFundsTest() {
		try {
			ba.withdraw(new BigDecimal("250.00"));	
			fail("Insufficient funds exception should be thrown");
		}
		catch(InsufficientFundsException ex) {
			// Correct exception thrown
		}
		catch(Exception ex) {
			fail("Unexpected exception thrown!");
		}
	}	
}
