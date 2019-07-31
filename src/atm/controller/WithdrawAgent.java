package atm.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import atm.model.BankAccount;
import atm.model.Receipt;
import atm.model.TransactionEvent;

public class WithdrawAgent implements Runnable, Agent{
	private Receipt receipt;
	private BankAccount bankAccount;
	private BigDecimal withdrawAmount;
	private BigDecimal transferred;
	private Exception RunException;
	 
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

	public Exception getRunException() {
		return RunException;
	}
	
	@Override
	public void run() {
		try {
			receipt.ProcessEvent(bankAccount, TransactionEvent.Balance, BigDecimal.ZERO);
			bankAccount.withdraw(withdrawAmount);
			transferred = transferred.add(withdrawAmount);
			receipt.ProcessEvent(bankAccount, TransactionEvent.Withdraw, transferred);
			receipt.ProcessEvent(bankAccount, TransactionEvent.Balance, BigDecimal.ZERO);
		}
		catch (Exception ex) {
			RunException = ex;
		}
	}

}
