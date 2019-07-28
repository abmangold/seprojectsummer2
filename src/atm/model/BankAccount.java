package atm.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankAccount extends AbstractModel{
	private BigDecimal Balance;
	private String Name;
	private String Owner;
	private String ID;
	private String PIN;

	public BankAccount() {
		this.Name = "";
		this.Owner = "";
		this.ID = "";
		this.PIN = "";
		this.Balance = BigDecimal.ZERO;	
		this.Balance.setScale(2, RoundingMode.HALF_UP);
}

	public BankAccount(String name,
					   String owner,
					   String ID, 
					   String PIN, 
					   BigDecimal balance) {
		this.Name = name;
		this.Owner = owner;
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

	public String getOwner() {
		return Owner;
	}

	public String getName() {
		return Name;
	}

	public BigDecimal getBalance() {
		return Balance;
	}

	public void withdraw(BigDecimal amount) throws InsufficientFundsException {
		BigDecimal newBalance = Balance.add(BigDecimal.ZERO);
		newBalance = newBalance.subtract(amount);
		if (newBalance.signum() < 0) throw new InsufficientFundsException();
		// Add Model Event
		Balance = newBalance;
		// Notify Changed
	}

	public void deposit(BigDecimal amount) {
		BigDecimal newBalance = Balance.add(BigDecimal.ZERO);
		newBalance = newBalance.add(amount);
		// Add Model Event
		Balance = newBalance;
		// Notify Changed
    }
	
	@Override
	public String toString()
	{
		return Name + "-" + ID;
	}
}
