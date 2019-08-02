package atm.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import atm.model.BankAccount;
import atm.model.Receipt;
import atm.model.TransactionEvent;

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
	
	@Override
	public void run() {
		try {
			receipt.ProcessEvent(origAccount, TransactionEvent.Balance, BigDecimal.ZERO);
			origAccount.withdraw(transferAmount);
			receipt.ProcessEvent(origAccount, TransactionEvent.Transfer_Withdraw, transferAmount);	
			receipt.ProcessEvent(origAccount, TransactionEvent.Balance, BigDecimal.ZERO);
			
			receipt.ProcessEvent(destAccount, TransactionEvent.Balance, BigDecimal.ZERO);
			destAccount.deposit(transferAmount);
			transferred = transferred.add(transferAmount);
			receipt.ProcessEvent(destAccount, TransactionEvent.Transfer_Deposit, transferAmount);
			receipt.ProcessEvent(destAccount, TransactionEvent.Balance, BigDecimal.ZERO);
		}
		catch (Exception ex) {
			RunException = ex;
		}
	}

}
