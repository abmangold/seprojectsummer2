package atm.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransferAgent implements Runnable, Agent{
	private Receipt receipt;
	private BankAccount origAccount;
	private BankAccount destAccount;
	private BigDecimal depositAmount;
	private BigDecimal transferred;
	 
	TransferAgent(BankAccount origAccount, BankAccount destAccount, BigDecimal amount) {
		this.origAccount = origAccount;
		this.destAccount = destAccount;
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
		return origAccount;
	}
	
	public BankAccount getDestBankAccount() {
		return destAccount;
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
