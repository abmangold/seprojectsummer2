package atm.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.Test;

import atm.controller.AccountVerification;


public class AccountVerificationTest {
	AccountVerification av;
	
	@Before
	public void setUp() throws Exception{
		av = new AccountVerification("12345", "1111");
		/*test1 = new AccountVerification("12345", "1111");
		test2 = new AccountVerification("12345", "1111");
		*/
	}
	@After
	public void endSession()throws Exception {
		av = null;
		/*test1 = null;
		test2 = null;
		test3 = null;*/
	}

	@Test
	public void testCorrectPin() {
	    //av = new AccountVerification("12345", "1111");
		av.setTestPin("1111");
		av.authenticate();
		assertFalse(av.checkLockStatus());
		
	}
	
	/*@Test
	public void testIncorrectPin() {
		av = new AccountVerification("12345", "1111");
		av.setTestPin("1234");
		av.authenticate();
		assertTrue(av.checkLockStatus());
	}
	
	@Test
	public void testLockedCard() {
		av = new AccountVerification("12345", "1111");
		av.setTestPin("1234");
		av.authenticate();
		av.authenticate();
	}*/
		
	

}
