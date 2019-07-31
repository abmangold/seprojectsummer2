package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.controller.TransferAgent;
import atm.model.BankAccount;

public class TransferAgentTest {

	BankAccount origAccount;
	BankAccount destAccount;
	TransferAgent ta;
	
	@Before
	public void Setup()
	{
		origAccount = new BankAccount("Checking", "123456", "1234", "1111", new BigDecimal("200.00"));
		destAccount = new BankAccount("Savings", "987654", "9876", "2222", new BigDecimal("50.00"));
	}
	
	@After
	public void Teardown()
	{
		origAccount = null;
		destAccount = null;
		ta = null;
	}
	
	@Test
	public void TransferTest()
	{
		BigDecimal transferAmount = new BigDecimal("25.55");
		transferAmount.setScale(2,RoundingMode.HALF_UP);
		ta = new TransferAgent(origAccount, destAccount, transferAmount);

		ta.run();

		assertEquals(transferAmount, ta.getTransferred());
		assertEquals("174.45", origAccount.getBalance().toString());
		assertEquals("75.55", destAccount.getBalance().toString());
	}
	
	@Test
	public void TransferInsufficientFundsTest()
	{
		BigDecimal transferAmount = new BigDecimal("250.00");
		transferAmount.setScale(2,RoundingMode.HALF_UP);
		ta = new TransferAgent(origAccount, destAccount, transferAmount);
		
		ta.run();

		assertEquals(BigDecimal.ZERO, ta.getTransferred());
		assertNotNull(ta.getRunException());
		assertEquals("Insufficient Funds!", ta.getRunException().getMessage());
		assertEquals("200.00", origAccount.getBalance().toString());
		assertEquals("50.00", destAccount.getBalance().toString());
	}
}
