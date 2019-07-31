package atm.tests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.controller.DepositAgent;
import atm.controller.TransferAgent;
import atm.controller.WithdrawAgent;
import atm.model.BankAccount;

public class ThreadSafeAgentTests {
	BankAccount account1;
	BankAccount account2;
	WithdrawAgent wa;
	DepositAgent da;
	TransferAgent ta;
	
	@Before
	public void Setup()
	{
		account1 = new BankAccount("Checking", "123456", "1234", "1111", new BigDecimal("200.00"));
		account2 = new BankAccount("Savings", "123456", "1235", "2222", new BigDecimal("1000.00"));
	}
	
	@After
	public void Teardown()
	{
		account1 = null;
		account2 = null;
		wa = null;
	}
	
	@Test
	public void ThreadSafeWithdrawDepositTest()
	{
		BigDecimal withdrawAmount = new BigDecimal("25.00");
		withdrawAmount.setScale(2,RoundingMode.HALF_UP);
		wa = new WithdrawAgent(account1, withdrawAmount);
		BigDecimal depositAmount = new BigDecimal("10.00");
		withdrawAmount.setScale(2,RoundingMode.HALF_UP);
		da = new DepositAgent(account1, depositAmount);

		Thread waThread = new Thread(wa);
		Thread daThread = new Thread(da);
		
		waThread.start();
		daThread.start();
		
		try {
			waThread.join();
			daThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(account1.getBalance().toString(), "185.00");
	}
}
