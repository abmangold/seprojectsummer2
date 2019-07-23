package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.model.BankAccount;
import atm.model.InsufficientFundsException;

public class BankAccountTest {
	BankAccount ba;
	@Before
	public void setUp() throws Exception {
		ba = new BankAccount("Checking", "123456", "12345", "1111", new BigDecimal("200.00"));
	}

	@After
	public void tearDown() throws Exception {
		ba = null;
	}
	
	@Test
	public void testWithdraw() throws Exception {
		ba.withdraw(new BigDecimal("110.55"));
		assertEquals(ba.getBalance().toString(), "89.45");
	}
	
	@Test
	public void testInsufficientFunds() throws Exception {
		try {
			ba.withdraw(new BigDecimal("250.00"));
			fail("Insufficient funds exception should be thrown");
		}
		catch(InsufficientFundsException ex) {
			assertEquals(ex.getMessage(), "Insufficient Funds!");
		}
	}
	
	@Test
	public void testDeposit() {
		ba.deposit(new BigDecimal("150.00"));
		assertEquals(ba.getBalance().toString(), "350.00");
	}
	
}
