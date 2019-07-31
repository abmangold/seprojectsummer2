package atm.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import atm.model.BankAccount;
import atm.model.Receipt;
import atm.model.TransactionEvent;

public class DepositAgent implements Runnable, Agent{
	private Receipt receipt;
	private BankAccount bankAccount;
	private BigDecimal depositAmount;
	private BigDecimal transferred;
	private Exception RunException;
	 
	public DepositAgent(BankAccount account, BigDecimal amount) {
		this.bankAccount = account;
		this.depositAmount = amount;
		this.depositAmount.setScale(2, RoundingMode.HALF_UP);
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
