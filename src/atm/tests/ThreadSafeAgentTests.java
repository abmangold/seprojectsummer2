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

/**
 * Tests to verify thread safety operations for various BankAccount Agents
 * @author Chris Carson
 */
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
				ArrayList<Thread> threads = new ArrayList<Thread>();
				boolean interruptedThread = false;
				for (int i=0; i < numTransactions; i++) {
					threads.add(new Thread(agent));
					threads.get(i).start();				
				}
				for(int i=0; i <numTransactions; i++)
				{
					try {
						threads.get(i).join();
					} catch (InterruptedException e) {
						interruptedThread = true;
					}
				}
				if (interruptedThread) {
					fail("Thread should not have interrupted!");
				}
			}
		});
	}
	
	@Before
	public void Setup()
	{
		account1 = new BankAccount("Checking", "1234", "1111", new BigDecimal("200.00"));
		account2 = new BankAccount("Savings", "1235", "2222", new BigDecimal("1000.00"));
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
		BigDecimal transferAmount = new BigDecimal("0.10");
		transferAmount.setScale(2,RoundingMode.HALF_UP);
		ta = new TransferAgent(account1, account2, transferAmount);

		Thread transferAgentsThread = SpawnAgentThreads(1000,ta);

		
		transferAgentsThread.start();
		
		try {
			transferAgentsThread.join();
		} catch (InterruptedException e) {
			fail("Should not have interrupted.");
		}
		
		assertEquals("100.00", account1.getBalance().toString());
		assertEquals("1100.00", account2.getBalance().toString());
	}
	
	@Test
	public void ThreadSafeDepositTest()
	{
		BigDecimal depositAmount = new BigDecimal("0.20");
		depositAmount.setScale(2,RoundingMode.HALF_UP);
		da = new DepositAgent(account1, depositAmount);

		Thread depositAgentsThread= SpawnAgentThreads(1000,da);

		
		depositAgentsThread.start();
		
		try {
			depositAgentsThread.join();
		} catch (InterruptedException e) {
			fail("Should not have interrupted.");
		}
		
		System.out.print(da.getTransferred());

		assertEquals("400.00", account1.getBalance().toString());
	}
	
	@Test
	public void ThreadSafeWithdrawTest()
	{
		BigDecimal withdrawAmount = new BigDecimal("0.15");
		withdrawAmount.setScale(2,RoundingMode.HALF_UP);
		wa = new WithdrawAgent(account1, withdrawAmount);

		Thread withdrawAgentsThread = SpawnAgentThreads(1000,wa);
		
		withdrawAgentsThread.start();
		
		try {
			withdrawAgentsThread.join();
		} catch (InterruptedException e) {
			fail("Should not have interrupted.");
		}
		
		assertEquals("50.00", account1.getBalance().toString());
	}
}
