package atm.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DepositAgent implements Runnable, Agent{
	private Receipt receipt;
	private BankAccount bankAccount;
	private BigDecimal depositAmount;
	private BigDecimal transferred;
	 
	DepositAgent(BankAccount account, BigDecimal amount) {
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

	@Override
	public void run() {
		// TODO Auto-generated method stub		
	}

}
