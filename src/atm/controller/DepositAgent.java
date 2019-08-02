package atm.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import atm.model.BankAccount;
import atm.model.Receipt;
import atm.model.TransactionEvent;

/**
 * DepositAgent class to perform deposits on a BankAccount.
 * @author Chris Carson
 */
public class DepositAgent implements Runnable, Agent{
	private Receipt receipt;
	private BankAccount bankAccount;
	private BigDecimal depositAmount;
	private BigDecimal transferred;
	private Exception RunException;
	 
	/**
	 * Default constructor for DepositAgent.
	 * @param account BankAccount to perform deposit on.
	 * @param amount BigDecimal amount to deposit into account.
	 */
	public DepositAgent(BankAccount account, BigDecimal amount) {
		this.bankAccount = account;
		this.depositAmount = amount;
		this.depositAmount.setScale(2, RoundingMode.HALF_UP);
		this.transferred = BigDecimal.ZERO;
		this.transferred.setScale(2, RoundingMode.HALF_UP);
		this.receipt = new Receipt();
		this.RunException = null;
	}
	
	@Override
	public Receipt getReceipt() {
		return receipt;
	}

	@Override
	public BankAccount getBankAccount() {
		return bankAccount;
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
			receipt.ProcessEvent(bankAccount, TransactionEvent.Balance, BigDecimal.ZERO);
			bankAccount.deposit(depositAmount);
			transferred = transferred.add(depositAmount);
			receipt.ProcessEvent(bankAccount, TransactionEvent.Deposit, transferred);
			receipt.ProcessEvent(bankAccount, TransactionEvent.Balance, BigDecimal.ZERO);
		}
		catch (Exception ex) {
			RunException = ex;
		}
	}

}
