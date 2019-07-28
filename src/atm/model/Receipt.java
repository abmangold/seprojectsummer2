package atm.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Receipt {

	private ArrayList<String> ReceiptLines = new ArrayList<String>();
	
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
	
	public ArrayList<String> getReceipt()
	{
		return ReceiptLines;
	}
	
}
