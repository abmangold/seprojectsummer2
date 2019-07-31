package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.controller.DepositAgent;
import atm.model.BankAccount;

public class DepositAgentTest {

	BankAccount ba;
	DepositAgent da;
	
	@Before
	public void Setup()
	{
		ba = new BankAccount("Checking", "123456", "1234", "1111", new BigDecimal("200.00"));
	}
	
	@After
	public void Teardown()
	{
		ba = null;
		da = null;
	}
	
	@Test
	public void DepositTest()
	{
		BigDecimal depositAmount = new BigDecimal("32.75");
		depositAmount.setScale(2,RoundingMode.HALF_UP);
		da = new DepositAgent(ba, depositAmount);

		da.run();

		assertEquals(depositAmount, da.getTransferred());
		assertEquals("232.75", ba.getBalance().toString());
	}
}
