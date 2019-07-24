package atm.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WithdrawAgent implements Runnable, Agent{
	private Receipt receipt;
	private BankAccount bankAccount;
	private BigDecimal withdrawAmount;
	private BigDecimal transferred;
	 
	WithdrawAgent(BankAccount account, BigDecimal amount) {
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
	public void run() {
		// TODO Auto-generated method stub		
	}

}
