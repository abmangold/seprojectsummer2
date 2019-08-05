package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.model.AccountLockException;
import atm.model.BankAccount;
import atm.controller.PinAuthenticator;

public class PinAuthenticatorTest {
	PinAuthenticator pinAuth;
	BankAccount ba;

	
	@Before
	public void setup() {
		ba = new BankAccount("Checking", "1234", "1111", BigDecimal.ZERO);
		pinAuth = new PinAuthenticator(ba);
	}
	
	@After
	public void tearDown() {
		ba = null;
		pinAuth = null;
	}

	@Test
	public void testCorrectPin() {
		boolean authResponse = false;
		try {
			authResponse = pinAuth.checkPin("1111");
		}
		catch (Exception ex) {
			fail("Should not have thrown exception.");
		}
		
		assertTrue(authResponse);
		assertFalse(ba.isAccountLock());
	}
	
	@Test
	public void testIncorrectPin() {
		boolean authResponse = true;
		try {
			authResponse = pinAuth.checkPin("2222");
		}
		catch (Exception ex) {
			fail("Should not have thrown exception.");
		}
		
		assertFalse(authResponse);
		assertFalse(ba.isAccountLock());
	}
	
	@Test
	public void testIncorrectPinAccountLock() {

		try {
			pinAuth.checkPin("2222");
			pinAuth.checkPin("3333");
			pinAuth.checkPin("4444");
			fail("Should have thrown AccountLockException.");			
		}
		catch (AccountLockException ex) {
			// Correct exception thrown
		}
		catch (Exception ex) {
			fail("Wrong exception thrown.");
		}
		
		assertTrue(ba.isAccountLock());
	}

}
