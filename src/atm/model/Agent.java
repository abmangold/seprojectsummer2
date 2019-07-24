package atm.model;

import java.math.BigDecimal;

public interface Agent{
	public Receipt getReceipt();
	public BankAccount getBankAccount();
	public BigDecimal getTransferred();
}
