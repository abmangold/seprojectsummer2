package atm.model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Receipt class that processes transaction events sent from Agents
 * @see atm.controller.DepositAgent
 * @see atm.controller.WithdrawAgent
 * @see atm.controller.TransferAgent
 * @author Chris Carson
 *
 */
public class Receipt {

	private ArrayList<String> ReceiptLines = new ArrayList<String>();
	
	
	/**
	 * Adds withdraw line items to the receipt.
	 * @param ba BankAccount withdrawn from.
	 * @param amount Amount that was withdrawn.
	 */
	public void addWithdraw(BankAccount ba, BigDecimal amount) {
		ReceiptLines.add("Withdraw from [" + ba.toString() + "]     -$" + amount.toString());
		ReceiptLines.add("");
		addBalance(ba);
	}
	
	/**
	 * Adds insufficient funds line items to the receipt.
	 * @param ba BankAccount withdrawn from.
	 * @param amount Amount that was withdrawn.
	 */
	public void addInsuffucientFundsMessage(BankAccount ba, BigDecimal amount) {
		ReceiptLines.add("Withdraw from [" + ba.toString() + "]     -$" + amount.toString());
		ReceiptLines.add("INSUFFICIENT FUNDS! Withdraw canceled.");
		ReceiptLines.add("");
		addBalance(ba);
	}
	
	/**
	 * Adds account lock line items to the receipt.
	 * @param ba BankAccount that is locked.
	 */
	public void addAccountLockMessage(BankAccount ba) {
		ReceiptLines.add("[" + ba.toString() + "] is locked no action can be performed.");
		ReceiptLines.add("");
	}
	
	/**
	 * Adds deposit line items to the receipt.
	 * @param ba BankAccount that was deposited into.
	 * @param amount Amount that was deposited.
	 */
	public void addDeposit(BankAccount ba, BigDecimal amount) {
		ReceiptLines.add("Deposit into [" + ba.toString() + "]     +$" + amount.toString());
		ReceiptLines.add("");
		addBalance(ba);
	}
	
	/**
	 * Adds transfer line items to the receipt.
	 * @param ba1 BankAccount transferred from.
	 * @param ba2 BankAccount transferred to.
	 * @param amount Amount that was transferred.
	 */
	public void addTransfer(BankAccount ba1, BankAccount ba2, BigDecimal amount) {
		ReceiptLines.add("Transfer from [" + ba1.toString() + "]     -$" + amount.toString());
		ReceiptLines.add("Transfer into [" + ba2.toString() + "]     +$" + amount.toString());
		ReceiptLines.add("");
		addBalance(ba1);
		addBalance(ba2);
	}
	
	/**
	 * Adds a balance line to the receipt.
	 * @param ba BankAccount to get balance. 
	 */
	public void addBalance(BankAccount ba) {
		ReceiptLines.add("[" + ba.toString() +"] Available Balance      $" + ba.getBalance().toString());
	}
	
	/**
	 * Generates a bank account balance Receipt.
	 * @param ba BankAccount to show balance of.
	 * @return Receipt showing the balance of the Bank Account
	 */
	public static Receipt getBalanceReceipt(BankAccount ba) {
		Receipt rec = new Receipt();
		rec.addBalance(ba);
		return rec;
	}

	/**
	 * Retrieves the ReceiptLines.
	 * @return ArrayList of ReceiptLines of the Receipt.
	 */
	public ArrayList<String> getReceiptLines() {
		return ReceiptLines;
	}
	
	
	/**
	 * Prints the receipt to System.out
	 */
	public void PrintReceipt()
	{
		System.out.println("#######################################################################");
		System.out.println("#####################         ATM RECEIPT        ######################");
		System.out.println("#######################################################################");
		System.out.println("#################    Scrooge McDuck Finacial Inc.    ##################");
		System.out.println("#######################################################################");
		System.out.println("");
		for(String line : ReceiptLines) {
			System.out.println(line);
		}
		System.out.println("");
		System.out.println("#######################################################################");
		System.out.println("#####################         End Receipt         #####################");
		System.out.println("#######################################################################");
		System.out.println("");
	}
}
