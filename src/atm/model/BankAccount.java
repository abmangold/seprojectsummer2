package atm.model;

import java.math.BigDecimal;

public class BankAccount extends AbstractModel{
	private BigDecimal Balance;
	private String Name;
	private String ID;
	private String PIN;
	public BankAccount(String name, 
					   String ID, 
					   String PIN, 
					   BigDecimal balance) {
		this.setName(Name);
		this.setID(ID);
		this.setPIN(PIN);
		this.setBalance(balance);		
	}
	public String getPIN() {
		return PIN;
	}
	private void setPIN(String PIN) {
		this.PIN = PIN;
	}
	public String getID() {
		return ID;
	}
	private void setID(String ID) {
		this.ID = ID;
	}
	public String getName() {
		return Name;
	}
	private void setName(String name) {
		this.Name = name;
	}
	public BigDecimal getBalance() {
		return Balance;
	}
	private void setBalance(BigDecimal balance) {
		this.Balance = balance;
	}
	
	public BigDecimal withdraw(BigDecimal amount) throws Exception {
		BigDecimal newBalance = Balance.add(BigDecimal.ZERO);
		newBalance.subtract(amount);
		if (newBalance.signum() < 0) throw new Exception("OverDraw");
		// Add Model Event
		Balance = newBalance;
		// Notify Changed
		return Balance;
	}
	
	public BigDecimal deposit(BigDecimal amount) {
		BigDecimal newBalance = Balance.add(BigDecimal.ZERO);
		newBalance.add(amount);
		// Add Model Event
		Balance = newBalance;
		// Notify Changed
		return Balance;
	}
}
