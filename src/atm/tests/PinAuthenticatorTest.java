package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.model.BankAccount;
import atm.controller.PinAuthenticator;

public class PinAuthenticatorTest {
	PinAuthenticator correct;
	PinAuthenticator wrong;
	
	@Before
	public void setup() {
		correct = new PinAuthenticator("1111");
		wrong = new PinAuthenticator("1234");
	}
	
	@After
	public void tearDown() {
		correct = null;
		wrong = null;
	}

	@Test
	public void testCorrectPin() {
		correct.checkPin();
		assertFalse(correct.checkLockStatus());
	}
	
	@Test
	public void testIncorrectPin() {
		wrong.checkPin();
		assertTrue(wrong.checkLockStatus());
	}

}
