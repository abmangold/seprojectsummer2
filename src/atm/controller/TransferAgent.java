package atm.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import atm.model.AccountLockException;
import atm.model.BankAccount;
import atm.model.InsufficientFundsException;
import atm.model.Receipt;

/**
 * TransferAgent class to perform transfers from one BankAccount to another BankAccount.
 * @author Chris Carson
 */
public class TransferAgent implements Runnable, Agent{
	private Receipt receipt;
	private BankAccount origAccount;
	private BankAccount destAccount;
	private BigDecimal transferAmount;
	private BigDecimal transferred;
	private Exception RunException;
	private Exception RunException2;
	 
	/** Default constructor for TransferAgent
	 * @param origAccount BankAccount to transfer (withdraw) from.
	 * @param destAccount BankAccount to transfer (deposit) to.
	 * @param amount BigDecimal amount to transfer.
	 */
	public TransferAgent(BankAccount origAccount, BankAccount destAccount, BigDecimal amount) {
		this.origAccount = origAccount;
		this.destAccount = destAccount;
		this.transferAmount = amount;
		this.transferAmount.setScale(2, RoundingMode.HALF_UP);
		this.transferred = BigDecimal.ZERO;
		this.transferred.setScale(2, RoundingMode.HALF_UP);
		this.receipt = new Receipt();	
	}
	
	@Override
	public Receipt getReceipt() {
		return receipt;
	}

	/** Retrieves the originating BankAccount
	 * @return BankAccount to transfer from.
	 */
	@Override
	public BankAccount getBankAccount() {
		return origAccount;
	}
	
	/** Retrieves the destination BankAccount
	 * @return BankAccount to transfer to.
	 */
	public BankAccount getDestBankAccount() {
		return destAccount;
	}

	@Override
	public BigDecimal getTransferred() {
		return transferred;
	}

	@Override
	public Exception getRunException() {
		return RunException;
	}
	
	/**
	 * Retrieves the Exception, if any, that were thrown on the destination account.
	 * @return RunException Exception thrown for destination account
	 */
	public Exception getRunException2() {
		return RunException2;
	}
	
	@Override
	public void run() {
		try {
			origAccount.withdraw(transferAmount);
			try {
				destAccount.deposit(transferAmount);
				transferred = transferred.add(transferAmount);
				receipt.addTransfer(origAccount, destAccount, transferred);
			}
			catch (AccountLockException ex) {
				origAccount.deposit(transferAmount); // undo transfer since destination account is locked.
				receipt.addAccountLockMessage(destAccount);
				RunException2 = ex;
			}				
		}
		catch (AccountLockException ex) {
			receipt.addAccountLockMessage(origAccount);
			RunException = ex;
		}
		catch (InsufficientFundsException ex) {
			receipt.addInsuffucientFundsMessage(origAccount, transferAmount);
			RunException = ex;
		}
	}

}
