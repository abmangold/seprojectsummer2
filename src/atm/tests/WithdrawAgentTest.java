package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.controller.WithdrawAgent;
import atm.model.BankAccount;

public class WithdrawAgentTest {

	BankAccount ba;
	WithdrawAgent wa;
	
	@Before
	public void Setup()
	{
		ba = new BankAccount("Checking", "123456", "1234", "1111", new BigDecimal("200.00"));
	}
	
	@After
	public void Teardown()
	{
		ba = null;
		wa = null;
	}
	
	@Test
	public void WithdrawTest()
	{
		BigDecimal withdrawAmount = new BigDecimal("25.55");
		withdrawAmount.setScale(2,RoundingMode.HALF_UP);
		wa = new WithdrawAgent(ba, withdrawAmount);

		wa.run();

		assertEquals(withdrawAmount, wa.getTransferred());
		assertEquals("174.45", ba.getBalance().toString());
	}
	
	@Test
	public void WithdrawInsufficientFundsTest()
	{
		BigDecimal withdrawAmount = new BigDecimal("250.00");
		withdrawAmount.setScale(2,RoundingMode.HALF_UP);
		wa = new WithdrawAgent(ba, withdrawAmount);
		
		wa.run();

		assertEquals(BigDecimal.ZERO, wa.getTransferred());
		assertNotNull(wa.getRunException());
		assertEquals("Insufficient Funds!", wa.getRunException().getMessage());
		assertEquals("200.00", ba.getBalance().toString());
	}
}
