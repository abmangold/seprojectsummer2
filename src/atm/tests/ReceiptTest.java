package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.model.BankAccount;
import atm.model.Receipt;

/**
 * Tests to verify Receipt correctness.
 * @author Chris Carson
 */
public class ReceiptTest {

	static private BankAccount ba1 = new BankAccount("Checking", "1111-2222-3333-4444", "111111", new BigDecimal("200.00"));
	static private BankAccount ba2 = new BankAccount("Savings", "5555-6666-7777-8888", "222222", new BigDecimal("100.00"));
	private Receipt receipt;
	
	@Before
	public void SetUp()
	{
		receipt = new Receipt();
	}
	
	@After
	public void TearDown()
	{
		receipt = null;
	}
	
	@Test
	public void AddWithdrawTest()
	{
		BigDecimal amount = new BigDecimal("50.00");
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Withdraw from [" + ba1.toString() + "]     -$" + amount.toString());
		expected.add("");
		expected.add("[" + ba1.toString() +"] Available Balance      $" + ba1.getBalance().toString());
		
		receipt.addWithdraw(ba1, amount);
		
		assertEquals(expected, receipt.getReceiptLines());
	}
	
	@Test
	public void AddDepositTest()
	{
		BigDecimal amount = new BigDecimal("50.00");
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Deposit into [" + ba1.toString() + "]     +$" + amount.toString());
		expected.add("");
		expected.add("[" + ba1.toString() +"] Available Balance      $" + ba1.getBalance().toString());
		
		receipt.addDeposit(ba1, amount);
		
		assertEquals(expected, receipt.getReceiptLines());
	}
	
	@Test
	public void AddTransferTest()
	{
		BigDecimal amount = new BigDecimal("50.00");
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Transfer from [" + ba1.toString() + "]     -$" + amount.toString());
		expected.add("Transfer into [" + ba2.toString() + "]     +$" + amount.toString());
		expected.add("");
		expected.add("[" + ba1.toString() +"] Available Balance      $" + ba1.getBalance().toString());
		expected.add("[" + ba2.toString() +"] Available Balance      $" + ba2.getBalance().toString());
		
		receipt.addTransfer(ba1, ba2, amount);
		
		assertEquals(expected, receipt.getReceiptLines());
	}
	
	@Test
	public void AddBalanceTest()
	{
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("[" + ba1.toString() +"] Available Balance      $" + ba1.getBalance().toString());
		
		receipt.addBalance(ba1);
		
		assertEquals(expected, receipt.getReceiptLines());
	}
	
	@Test
	public void AddAccountLockMessageTest()
	{
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("[" + ba1.toString() + "] is locked no action can be performed.");
		expected.add("");
		
		receipt.addAccountLockMessage(ba1);
		
		assertEquals(expected, receipt.getReceiptLines());
	}
	
	@Test
	public void AddInsufficientFundsMessageTest()
	{
		BigDecimal amount = new BigDecimal("500.00");
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Withdraw from [" + ba1.toString() + "]     -$" + amount.toString());
		expected.add("INSUFFICIENT FUNDS! Withdraw canceled.");
		expected.add("");
		expected.add("[" + ba1.toString() +"] Available Balance      $" + ba1.getBalance().toString());
		
		receipt.addInsufficientFundsMessage(ba1, amount);
		
		assertEquals(expected, receipt.getReceiptLines());
	}
	
	@Test
	public void GetBalanceReceiptTest()
	{
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("[" + ba1.toString() +"] Available Balance      $" + ba1.getBalance().toString());
		
		Receipt receipt = Receipt.getBalanceReceipt(ba1);
		
		assertEquals(expected, receipt.getReceiptLines());
	}
	
	@Test
	public void GetBalanceReceiptAccountLockTest()
	{
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("[" + ba1.toString() + "] is locked no action can be performed.");
		expected.add("");
		
		ba1.setAccountLock(true);
		Receipt receipt = Receipt.getBalanceReceipt(ba1);
		ba1.setAccountLock(false);
		
		assertEquals(expected, receipt.getReceiptLines());
	}
}
