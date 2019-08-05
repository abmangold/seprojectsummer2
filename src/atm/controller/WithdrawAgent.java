package atm.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import atm.model.BankAccount;
import atm.model.Receipt;

/**
 * WithdrawAgent class to perform withdraws on a BankAccount.
 * @author Chris Carson
 */
public class WithdrawAgent implements Runnable, Agent{
	private Receipt receipt;
	private BankAccount bankAccount;
	private BigDecimal withdrawAmount;
	private BigDecimal transferred;
	private Exception RunException;
	
	/**
	 * Default constructor for WithdrawAgent.
	 * @param account BankAccount to perform withdraw on.
	 * @param amount BigDecimal amount to withdraw from account.
	 */
	public WithdrawAgent(BankAccount account, BigDecimal amount) {
		this.bankAccount = account;
		this.withdrawAmount = amount;
		this.withdrawAmount.setScale(2, RoundingMode.HALF_UP);
		this.transferred = BigDecimal.ZERO;
		this.transferred.setScale(2, RoundingMode.HALF_UP);
		this.receipt = new Receipt();	
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
			bankAccount.withdraw(withdrawAmount);
			transferred = transferred.add(withdrawAmount);
			receipt.addWithdraw(bankAccount, transferred);
		}
		catch (Exception ex) {
			RunException = ex;
		}
	}

}
