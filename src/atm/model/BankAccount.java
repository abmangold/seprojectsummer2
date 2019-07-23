package atm.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankAccount extends AbstractModel{
	private BigDecimal Balance;
	private String Name;
	private String ID;
	private String PIN;
	public BankAccount(String name, 
					   String ID, 
					   String PIN, 
					   BigDecimal balance) {
		this.Name = name;
		this.ID = ID;
		this.PIN = PIN;
		this.Balance = balance;	
		this.Balance.setScale(2, RoundingMode.HALF_UP);
	}
	public String getPIN() {
		return PIN;
	}
	public String getID() {
		return ID;
	}
	public String getName() {
		return Name;
	}
	public BigDecimal getBalance() {
		return Balance;
	}
	
	public BigDecimal withdraw(BigDecimal amount) throws Exception {
		BigDecimal newBalance = Balance.add(BigDecimal.ZERO);
		newBalance = newBalance.subtract(amount);
		if (newBalance.signum() < 0) throw new Exception("OverDraw");
		// Add Model Event
		Balance = newBalance;
		// Notify Changed
		return Balance;
	}
	
	public BigDecimal deposit(BigDecimal amount) {
		BigDecimal newBalance = Balance.add(BigDecimal.ZERO);
		newBalance = newBalance.add(amount);
		// Add Model Event
		Balance = newBalance;
		// Notify Changed
		return Balance;
	}
}
