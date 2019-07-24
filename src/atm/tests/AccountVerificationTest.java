package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.controller.AccountVerification;
import atm.model.BankAccount;


public class AccountVerificationTest {
	AccountVerification av;
	
	@Before
	public void setUp() throws Exception{
		av = new AccountVerification("12345", "1111");
	}
	@After
	public void endSession()throws Exception {
		av = null;
	}

	@Test
	public void testCorrectPin() {
		av.authenticate();
		assertFalse(av.checkLockStatus());
		
	}
	
	@Test
	public void testIncorrectPin() {
		av.setTestPin("1234");
		av.authenticate();
		assertTrue(av.checkLockStatus());
	}
	
	@Test
	public void testLockedCard() {
		av.setTestPin("1234");
		av.authenticate();
		av.authenticate();
	}
		
	

}
