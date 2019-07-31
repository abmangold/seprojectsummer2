package atm.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankAccount {
	private BigDecimal Balance;
	private String Name;
	private String Owner;
	private String ID;
	private String PIN;
	private boolean AccountLock;
	private Object SyncLock;

	public BankAccount() {
		this("", "", "", "", BigDecimal.ZERO);
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
		this.SyncLock = new Object();
		this.setAccountLock(false);
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

	public void withdraw(BigDecimal amount) throws InsufficientFundsException, AccountLockException {
		synchronized(SyncLock) {
			if (AccountLock) {
				throw new AccountLockException();
			}
			BigDecimal newBalance = Balance.add(BigDecimal.ZERO);
			newBalance = newBalance.subtract(amount);
			if (newBalance.signum() < 0) throw new InsufficientFundsException();
			Balance = newBalance;
		}
	}

	public void deposit(BigDecimal amount) throws AccountLockException {
		synchronized(SyncLock) {
			if (AccountLock) {
				throw new AccountLockException();
			}
			BigDecimal newBalance = Balance.add(BigDecimal.ZERO);
			newBalance = newBalance.add(amount);
			Balance = newBalance;
		}
    }
	
	@Override
	public String toString()
	{
		return Name + "-" + ID;
	}

	public boolean isAccountLock() {
			return AccountLock;
	}

	public void setAccountLock(boolean accountLock) {
		synchronized(SyncLock) {
			AccountLock = accountLock;
		}
	}
}
