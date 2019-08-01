package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.model.BankAccount;
import atm.model.UserAccount;

public class UserAccountTest {

	private UserAccount ua;
	
	@Before
	public void Setup()
	{
		ua = new UserAccount("Chris", "1234");
	}
	
	@After
	public void TearDown() {
		ua = null;
	}
	
	@Test
	public void AddBankAccountTest()
	{
		BankAccount ba = new BankAccount("Checking", "1234", "1111", BigDecimal.ZERO);
		ua.AddBankAccount(ba);
		assertEquals(ua, ba.getOwner());
		assertEquals(ba, ua.GetBankAccounts().get(0));
	}
}
