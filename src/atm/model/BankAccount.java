package atm.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * BankAccount class containing bank account information (name, id, balance, etc.) and methods to manipulate balance.
 * @author Chris Carson
 *  
 */
public class BankAccount {
	
	private BigDecimal Balance;
	private String Name;
	private UserAccount Owner;
	private String ID;
	private String PIN;
	private boolean AccountLock;
	private Object SyncLock;

	/**
	 * Default constructor for BankAccount 
	 * 
	 * @param name Name of the bank account. (i.e Checking, Savings, etc.)
	 * @param ID Identifier of the bank account.
	 * @param PIN Personal Identification Number used to authenticate ownership of account.
	 * @param balance Total money in a bank account.
	 */
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
	
	
	/**
	 * Retrieves the BankAccount owner.
	 * @see atm.model.UserAccount
	 * @return  UserAccount owner of the BankAccount.
	 */
	public UserAccount getOwner() {
		return Owner;
	}

	/**
	 * Sets an owner of the BankAccount.
	 * @see atm.model.UserAccount
	 * @param owner UserAccount to set as owner.
	 */ 
	public void setOwner(UserAccount owner) {
		Owner = owner;
	}
	
	/**
	 * Retrieves the BankAccount PIN
	 * @return String of the PIN for the BankAccount
	 */
	public String getPIN() {
		return PIN;
	}

	/**
	 * Retrieves the BankAccount ID
	 * @return String of the ID for the BankAccount
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Retrieves the BankAccount Name
	 * @return String of the Name for the BankAccount
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Retrieves the BankAccount Balance
	 * @return BigDecimal balance for the BankAccount
	 */
	public BigDecimal getBalance() {
		return Balance;
	}

	/** Retrieves the BankAccount lock status.
	 * @return Boolean of AccountLock status
	 */
	public boolean isAccountLock() {
			return AccountLock;
	}

	/**
	 * Sets the lock status of the BankAccount
	 * @param accountLock Boolean to set lock status
	 */
	public void setAccountLock(boolean accountLock) {
		synchronized(SyncLock) {
			AccountLock = accountLock;
		}
	}
	
	/**
	 * Performs a withdraw on the account decrementing Balance by amount.
	 * @param amount BigDecimal amount to withdraw.
	 * @throws InsufficientFundsException Withdraw &gt; Balance.
	 * @throws AccountLockException BankAccount is locked.
	 */
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

	/**
	 * Performs a deposit on the account incrementing Balance by amount.
	 * @param amount BigDecimal amount to deposit.
	 * @throws AccountLockException BankAccount is locked.
	 */
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

}
