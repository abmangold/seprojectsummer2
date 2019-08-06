package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.model.Bank;
import atm.model.BankAccount;
import atm.model.UserAccount;

/**
 * Tests to verify Bank correctness.
 * @author Chris Carson
 */
public class BankTest {

	BankAccount ba1 = new BankAccount("Checking", "1111-1111-1111-1111", "111111", BigDecimal.ZERO);
	BankAccount ba2 = new BankAccount("Savings", "2222-2222-2222-2222", "222222", BigDecimal.ZERO);
	BankAccount ba3 = new BankAccount("Checking", "3333-3333-3333-3333", "333333", BigDecimal.ZERO);
	UserAccount ua1;
	UserAccount ua2;
	Bank bank;
	
	@Before
	public void SetUp() {		
		ua1 = new UserAccount("John Smith", "1111-2222-3333-4444");
		ua1.AddBankAccount(ba1);
		ua1.AddBankAccount(ba2);
		
		ua2 = new UserAccount("Jane Doe", "5555-6666-7777-8888");
		ua2.AddBankAccount(ba3);
		
		bank = new Bank();
	}
	
	@After
	public void TearDown() {
		ua1 = null;
		ua2 = null;
		bank = null;
	}
	
	@Test
	public void GetUserAccountTest()
	{
		bank.addUsers(ua1);
		bank.addUsers(ua2);
		
		UserAccount actual = bank.getUserAccount("1111-2222-3333-4444");
		
		assertEquals(ua1, actual);
	}
	
	@Test
	public void GetBankAccountTest()
	{
		bank.addBankAccount(ba1);
		bank.addBankAccount(ba2);
		bank.addBankAccount(ba3);
		
		BankAccount actual = bank.getBankAccount("2222-2222-2222-2222");
		
		assertEquals(ba2, actual);
	}
	
	@Test 
	public void AddUserWithBankAccountTest()
	{
		bank.addUsers(ua1);

		ArrayList<BankAccount> accounts = bank.getBankAccounts();
		
		assertTrue(accounts.contains(ba1));
		assertTrue(accounts.contains(ba2));		
	}
	
	@Test 
	public void GetUserBankAccountsTest()
	{
		bank.addUsers(ua2);

		ArrayList<BankAccount> accounts = bank.getUserBankAccounts("5555-6666-7777-8888");
		
		assertTrue(accounts.contains(ba3));	
	}
	
	@Test 
	public void GetUserAccountNotFoundTest()
	{
		bank.addUsers(ua2);

		UserAccount actual = bank.getUserAccount("1111-2222-3333-4444");
		
		assertNull(actual);	
	}
	
	@Test 
	public void GetBankAccountNotFoundTest()
	{
		bank.addBankAccount(ba1);

		BankAccount actual = bank.getBankAccount("7777-7777-7777-7777");
		
		assertNull(actual);	
	}
}
