package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.model.BankAccount;
import atm.model.Receipt;
import atm.controller.TransactionManager;

/**
 * Tests to verify TransactionManager correctness.
 * @author Chris Carson
 *
 */
public class TransactionManagerTest {

	BankAccount ba1;
	BankAccount ba2;
	
	@Before
	public void Setup()
	{
		ba1 = new BankAccount("Checking", "1234", "1111", new BigDecimal("200.00"));
		ba2 = new BankAccount("Savings", "9876", "2222", new BigDecimal("50.00"));
	}
	
	@After
	public void Teardown()
	{
		ba1 = null;
		ba2 = null;
	}
	
	@Test
	public void TransactionManagerWithdrawTest()
	{
		Receipt rec = new Receipt();
		BigDecimal amount = new BigDecimal("50.00");
		
		Receipt actual = TransactionManager.withdraw(ba1, amount);
		rec.addWithdraw(ba1, amount);
		
		assertEquals(new BigDecimal("150.00"), ba1.getBalance());
		assertEquals(rec.getReceiptLines(), actual.getReceiptLines());
	}
	
	@Test
	public void TransactionManagerWithdrawInsufficentFundsTest()
	{
		Receipt rec = new Receipt();
		BigDecimal amount = new BigDecimal("500.00");
		
		Receipt actual = TransactionManager.withdraw(ba1, amount);
		rec.addInsufficientFundsMessage(ba1, amount);
		
		assertEquals(new BigDecimal("200.00"), ba1.getBalance());
		assertEquals(rec.getReceiptLines(), actual.getReceiptLines());
	}
	
	@Test
	public void TransactionManagerWithdrawAccountLockTest()
	{
		Receipt rec = new Receipt();
		BigDecimal amount = new BigDecimal("50.00");
		ba1.setAccountLock(true);
		
		Receipt actual = TransactionManager.withdraw(ba1, amount);
		rec.addAccountLockMessage(ba1);
		
		assertEquals(new BigDecimal("200.00"), ba1.getBalance());
		assertEquals(rec.getReceiptLines(), actual.getReceiptLines());
	}
	
	@Test
	public void TransactionManagerDepositTest()
	{
		Receipt rec = new Receipt();
		BigDecimal amount = new BigDecimal("50.00");
		
		Receipt actual = TransactionManager.deposit(ba1, amount);
		rec.addDeposit(ba1, amount);
		
		assertEquals(new BigDecimal("250.00"), ba1.getBalance());
		assertEquals(rec.getReceiptLines(), actual.getReceiptLines());
	}
	
	@Test
	public void TransactionManagerDepositAccountLockTest()
	{
		Receipt rec = new Receipt();
		BigDecimal amount = new BigDecimal("50.00");
		ba1.setAccountLock(true);
		
		Receipt actual = TransactionManager.deposit(ba1, amount);
		rec.addAccountLockMessage(ba1);
		
		assertEquals(new BigDecimal("200.00"), ba1.getBalance());
		assertEquals(rec.getReceiptLines(), actual.getReceiptLines());
	}
	
	@Test
	public void TransactionManagerTransferTest()
	{
		Receipt rec = new Receipt();
		BigDecimal amount = new BigDecimal("50.00");
		
		Receipt actual = TransactionManager.transfer(ba1,ba2, amount);
		rec.addTransfer(ba1, ba2, amount);
		
		assertEquals(new BigDecimal("150.00"), ba1.getBalance());
		assertEquals(new BigDecimal("100.00"), ba2.getBalance());
		assertEquals(rec.getReceiptLines(), actual.getReceiptLines());
	}
	
	@Test
	public void TransactionManagerTransferInsufficentFundsTest()
	{
		Receipt rec = new Receipt();
		BigDecimal amount = new BigDecimal("500.00");
		
		Receipt actual = TransactionManager.transfer(ba1,ba2, amount);
		rec.addInsufficientFundsMessage(ba1, amount);
		
		assertEquals(new BigDecimal("200.00"), ba1.getBalance());
		assertEquals(new BigDecimal("50.00"), ba2.getBalance());
		assertEquals(rec.getReceiptLines(), actual.getReceiptLines());
	}
	
	@Test
	public void TransactionManagerTransferOrigAccountLockTest()
	{
		Receipt rec = new Receipt();
		BigDecimal amount = new BigDecimal("50.00");
		ba1.setAccountLock(true);
		
		Receipt actual = TransactionManager.transfer(ba1, ba2, amount);
		rec.addAccountLockMessage(ba1);
		
		assertEquals(new BigDecimal("200.00"), ba1.getBalance());
		assertEquals(new BigDecimal("50.00"), ba2.getBalance());
		assertEquals(rec.getReceiptLines(), actual.getReceiptLines());
	}
	
	@Test
	public void TransactionManagerTransferDestAccountLockTest()
	{
		Receipt rec = new Receipt();
		BigDecimal amount = new BigDecimal("50.00");
		ba2.setAccountLock(true);
		
		Receipt actual = TransactionManager.transfer(ba1, ba2, amount);
		rec.addAccountLockMessage(ba2);
		
		assertEquals(new BigDecimal("200.00"), ba1.getBalance());
		assertEquals(new BigDecimal("50.00"), ba2.getBalance());
		assertEquals(rec.getReceiptLines(), actual.getReceiptLines());
	}
	
	@Test
	public void TransactionManagerShowBalanceTest()
	{
		Receipt rec = new Receipt();
		
		Receipt actual = TransactionManager.showBalance(ba1);
		rec.addBalance(ba1);
		
		assertEquals(new BigDecimal("200.00"), ba1.getBalance());
		assertEquals(rec.getReceiptLines(), actual.getReceiptLines());
	}
	
	@Test
	public void TransactionManagerShowBalanceAccountLockTest()
	{
		
		Receipt rec = new Receipt();
		ba1.setAccountLock(true);
		
		Receipt actual = TransactionManager.showBalance(ba1);
		rec.addAccountLockMessage(ba1);
		
		assertEquals(new BigDecimal("200.00"), ba1.getBalance());
		assertEquals(rec.getReceiptLines(), actual.getReceiptLines());
	}
}
