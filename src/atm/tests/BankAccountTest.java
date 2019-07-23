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
		ba = new BankAccount("Checking", "123456", "1111", new BigDecimal("200.00"));
	}

	@After
	public void tearDown() throws Exception {
		ba = null;
	}
	
	@Test
	public void testWithdraw() throws Exception {
		BigDecimal Ba = ba.withdraw(new BigDecimal("110.55"));
		System.out.println("withdraw, new balance = " + Ba.toString());
		assertEquals(ba.getBalance().toString(), "89.45");
	}
	
	@Test
	public void testDeposit() {
		BigDecimal Ba = ba.deposit(new BigDecimal("150.00"));
		System.out.println("deposit, new balance = " + Ba.toString());
		assertEquals(ba.getBalance().toString(), "350.00");
	}
	
}
