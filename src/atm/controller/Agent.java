package atm.controller;

import java.math.BigDecimal;

import atm.model.BankAccount;
import atm.model.Receipt;

public interface Agent extends Runnable{
	public Receipt getReceipt();
	public BankAccount getBankAccount();
	public BigDecimal getTransferred();
}
