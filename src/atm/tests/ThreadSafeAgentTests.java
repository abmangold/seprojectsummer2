package atm.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.controller.Agent;
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
	
	Thread SpawnAgentThreads(int numTransactions, Agent agent) {	
		return new Thread(new Runnable() {
			@Override
			public void run() {
				Random random = new Random();
				for (int i=0; i < numTransactions; i++) {
					try {
					Thread.sleep(random.nextInt(2000));
					Thread agentThread = new Thread(agent);
					agentThread.start();
					agentThread.join();
					} catch (InterruptedException e) {
						fail("Should not have interrupted.");
					}
				}
			}
		});
	}
	
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
		da = null;
		ta = null;
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
			fail("Should not have interrupted.");
		}
		
		assertEquals("185.00", account1.getBalance().toString());
	}
	
	@Test
	public void ThreadSafeWithdrawTransferTest()
	{
		BigDecimal withdrawAmount = new BigDecimal("25.00");
		withdrawAmount.setScale(2,RoundingMode.HALF_UP);
		wa = new WithdrawAgent(account1, withdrawAmount);
		
		BigDecimal transferAmount = new BigDecimal("10.00");
		transferAmount.setScale(2,RoundingMode.HALF_UP);
		ta = new TransferAgent(account1, account2, transferAmount);

		Thread waThread = new Thread(wa);
		Thread taThread = new Thread(ta);
		
		waThread.start();
		taThread.start();
		
		try {
			waThread.join();
			taThread.join();
		} catch (InterruptedException e) {
			fail("Should not have interrupted.");
		}
		
		assertEquals("165.00", account1.getBalance().toString());
		assertEquals("1010.00", account2.getBalance().toString());
	}
	
	@Test
	public void ThreadSafeDepositTransferTest()
	{
		BigDecimal depositAmount = new BigDecimal("25.00");
		depositAmount.setScale(2,RoundingMode.HALF_UP);
		da = new DepositAgent(account2, depositAmount);
		
		BigDecimal transferAmount = new BigDecimal("10.00");
		transferAmount.setScale(2,RoundingMode.HALF_UP);
		ta = new TransferAgent(account1, account2, transferAmount);

		Thread daThread = new Thread(da);
		Thread taThread = new Thread(ta);
		
		daThread.start();
		taThread.start();
		
		try {
			daThread.join();
			taThread.join();
		} catch (InterruptedException e) {
			fail("Should not have interrupted.");
		}
		
		assertEquals("190.00", account1.getBalance().toString());
		assertEquals("1035.00", account2.getBalance().toString());
	}
	
	@Test
	public void ThreadSafeTransferTest()
	{
		BigDecimal transferAmount = new BigDecimal("10.00");
		transferAmount.setScale(2,RoundingMode.HALF_UP);
		ta = new TransferAgent(account1, account2, transferAmount);

		Thread transferAgentsThread1 = SpawnAgentThreads(3,ta);
		Thread transferAgentsThread2 = SpawnAgentThreads(2,ta);
		
		transferAgentsThread1.start();
		transferAgentsThread2.start();
		
		try {
			transferAgentsThread1.join();
			transferAgentsThread2.join();
		} catch (InterruptedException e) {
			fail("Should not have interrupted.");
		}
		
		assertEquals("150.00", account1.getBalance().toString());
		assertEquals("1050.00", account2.getBalance().toString());
	}
	
	@Test
	public void ThreadSafeDepositTest()
	{
		BigDecimal depositAmount = new BigDecimal("20.00");
		depositAmount.setScale(2,RoundingMode.HALF_UP);
		da = new DepositAgent(account1, depositAmount);

		Thread depositAgentsThread1 = SpawnAgentThreads(3,da);
		Thread depositAgentsThread2 = SpawnAgentThreads(2,da);
		
		depositAgentsThread1.start();
		depositAgentsThread2.start();
		
		try {
			depositAgentsThread1.join();
			depositAgentsThread2.join();
		} catch (InterruptedException e) {
			fail("Should not have interrupted.");
		}
		
		System.out.print(da.getTransferred());

		assertEquals("300.00", account1.getBalance().toString());
	}
	
	@Test
	public void ThreadSafeWithdrawTest()
	{
		BigDecimal withdrawAmount = new BigDecimal("15.00");
		withdrawAmount.setScale(2,RoundingMode.HALF_UP);
		wa = new WithdrawAgent(account1, withdrawAmount);

		Thread withdrawAgentsThread1 = SpawnAgentThreads(3,wa);
		Thread withdrawAgentsThread2 = SpawnAgentThreads(2,wa);
		
		withdrawAgentsThread1.start();
		withdrawAgentsThread2.start();
		
		try {
			withdrawAgentsThread1.join();
			withdrawAgentsThread2.join();
		} catch (InterruptedException e) {
			fail("Should not have interrupted.");
		}
		
		assertEquals("125.00", account1.getBalance().toString());
	}
}
