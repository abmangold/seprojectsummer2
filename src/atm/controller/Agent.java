package atm.controller;

import java.math.BigDecimal;

import atm.model.BankAccount;
import atm.model.Receipt;

/**
 * Interface for BankAccount Agents.
 * @author Chris Carson
 *
 */
public interface Agent extends Runnable{
	
	/**
	 * Retrieves the Agent Receipt.
	 * @see atm.model.Receipt
	 * @return Receipt for actions performed by the Agent.
	 */
	public Receipt getReceipt();
	
	/**
	 * Retrieves the Agent BankAccount.
	 * @see atm.model.BankAccount
	 * @return BankAccount to perform action on.
	 */
	public BankAccount getBankAccount();
	
	/**
	 * Retrieves the Agent transfer amount.
	 * @return BigDecimal of the amount transferred
	 */
	public BigDecimal getTransferred();
	
	/**
	 * Retrieves the Exception, if any, that were thrown during an Agent run.
	 * @return Exception thrown during running, null if none were thrown.
	 */
	public Exception getRunException();
}
