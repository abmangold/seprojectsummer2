package atm.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;


public class BankAccount {
	private BigDecimal Balance;
	private String Name;
	private UserAccount Owner;
	private String ID;
	private String PIN;
	private boolean AccountLock;
	private Object SyncLock;

	public BankAccount() {
		this("", "", "", BigDecimal.ZERO);
	}

	public BankAccount(String name,
					   String ID, 
					   String PIN, 
					   BigDecimal balance) {
		this.Name = name;
		this.ID = ID;
		this.PIN = PIN;
		this.Balance = balance;	
		this.Balance.setScale(2, RoundingMode.HALF_UP);
		this.SyncLock = new Object();
		this.setAccountLock(false);
	}
	
	public UserAccount getOwner() {
		return Owner;
	}

	public void setOwner(UserAccount owner) {
		Owner = owner;
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

	public void withdraw(BigDecimal amount) throws InsufficientFundsException, AccountLockException {
		synchronized(SyncLock) {
			if (AccountLock) {
				throw new AccountLockException();
			}
			try {
				Thread.sleep(new Random().nextInt(250)); // simulate database connection
			} catch (InterruptedException e) {
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
			try {
				Thread.sleep(new Random().nextInt(250)); // simulate database connection
			} catch (InterruptedException e) {
				e.printStackTrace();
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
