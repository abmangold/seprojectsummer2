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
	 * Processes a transaction event on an Account and adds it the ReceiptLines list.
	 * @see atm.model.TransactionEvent
	 * @param account BankAccount the transaction is being performed on.
	 * @param event The type of TransactionEvent.
	 * @param amount The amount applied to the account based on event.
	 */
	public void ProcessEvent(BankAccount account, TransactionEvent event, BigDecimal amount) {
		String receiptLine = "(" + account.toString() + ")";
		switch(event) {
		case Withdraw:
			receiptLine = "$" + amount + " withdrawn from " + receiptLine;
			break;
		case Deposit:
			receiptLine = "$" + amount + " deposit from " + receiptLine;
			break;
		case Transfer_Withdraw:
			receiptLine = "$" + amount + " transfered from " + receiptLine;
			break;
		case Transfer_Deposit:
			receiptLine = "$" + amount + " transfered to " + receiptLine;
			break;
		case Balance:
			receiptLine = "$"+ account.getBalance() +"Balance "+ receiptLine;
			break;
		}
		ReceiptLines.add(receiptLine);
	}
	
	
	/**
	 * Retrieves the ReceiptLines.
	 * @return ArrayList of ReceiptLines of the Receipt.
	 */
	public ArrayList<String> getReceipt()
	{
		return ReceiptLines;
	}
	
}
