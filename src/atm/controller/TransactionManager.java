package atm.controller;

import java.math.BigDecimal;

import atm.model.AccountLockException;
import atm.model.BankAccount;
import atm.model.InsufficientFundsException;
import atm.model.Receipt;

/**
 * Manages account transaction actions and receipts.
 * @see atm.model.Receipt
 * @author Chris Carson
 */
public class TransactionManager {

	/**
	 * Performs a withdraw.
	 * @param ba BankAccount to withdraw from.
	 * @param amount BigDecimal amount to withdraw.
	 * @return Receipt of actions performed on the account.
	 */
	public static Receipt withdraw(BankAccount ba, BigDecimal amount) {
		WithdrawAgent agent = new WithdrawAgent(ba, amount);
		Thread agentThread = new Thread(agent);
		
		agentThread.start();
		try {
			agentThread.join();
		} catch (InterruptedException e) {
			System.out.println("Withdraw Failed!");
		}

		if (agent.getRunException() != null) {
			Exception ex = agent.getRunException();
			if (ex instanceof InsufficientFundsException) {
				
			}
			if (ex instanceof AccountLockException) {			
			}
		}
		return agent.getReceipt();	
	}
	
	/**
	 * Performs a deposit.
	 * @param ba BankAccount to deposit into.
	 * @param amount BigDecimal amount to deposit.
	 * @return Receipt of actions performed on the account.
	 */
	public static Receipt deposit(BankAccount ba, BigDecimal amount) {
		DepositAgent agent = new DepositAgent(ba, amount);
		Thread depositThread = new Thread(agent);
		
		depositThread.start();
		try {
			depositThread.join();
		} catch (InterruptedException e) {
			System.out.println("Deposit Failed!");
		}

		if (agent.getRunException() != null) {
			Exception ex = agent.getRunException();
			if (ex instanceof AccountLockException) {
				
			}
		}
		return agent.getReceipt();	
	}
	

	/**
	 * Performs a transfer between accounts.
	 * @param origAccount BankAccount to transfer from.
	 * @param destAccount BankAccount to transfer to.
	 * @param amount BigDecimal amount to transfer.
	 * @return Receipt of actions performed on the account.
	 */
	public static Receipt transfer(BankAccount origAccount, BankAccount destAccount, BigDecimal amount){
		TransferAgent agent = new TransferAgent(origAccount, destAccount, amount);
		Thread agentThread = new Thread(agent);
		
		agentThread.start();
		try {
			agentThread.join();
		} catch (InterruptedException e) {
			System.out.println("Transfer Failed!");
		}

		if (agent.getRunException() != null) {
			Exception ex = agent.getRunException();
			if (ex instanceof InsufficientFundsException) {
				
			}
			if (ex instanceof AccountLockException) {
				
			}
		}
		return agent.getReceipt();	
	}
	
	/** Performs a balance inquiry on the account.
	 * @param ba BankAccount to show balance of.
	 * @return Receipt of a balance inquiry
	 */
	public static Receipt showBalance(BankAccount ba) {

		return Receipt.getBalanceReceipt(ba);
	}
}
