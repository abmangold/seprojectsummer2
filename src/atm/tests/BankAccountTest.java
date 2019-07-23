package atm.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import atm.model.BankAccount;

public class BankAccountTest {
	BankAccount ba;
	@Before
	public void setUp() throws Exception {
		ba = new BankAccount("Checking", "123456", "12345", "1111", new BigDecimal("200.00"));
	}

	@After
	public void tearDown() throws Exception {
		ba = null;
	}
	
	@Test
	public void testWithdraw() throws Exception {
		ba.withdraw(new BigDecimal("110.55"));
		assertEquals(ba.getBalance().toString(), "89.45");
	}
	
	@Test
	public void testDeposit() {
		ba.deposit(new BigDecimal("150.00"));
		assertEquals(ba.getBalance().toString(), "350.00");
	}
	
}
