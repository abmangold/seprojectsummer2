package atm.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import atm.controller.PinAuthenticator;
import atm.controller.TransactionManager;
import atm.model.AccountLockException;
import atm.model.Bank;
import atm.model.BankAccount;
import atm.model.UserAccount;

/**
 * Console Application for Acceptance testing and demo.
 * @author Chris Carson
 *
 */
public class View {
	static Scanner s = new Scanner(System.in);

	/**
	 * Main console application entry point.
	 */
	public static void main(String[] args)
	{
		atm();
	}

	/**
	 * Main control loop
	 */
	public static void atm() {
		Bank bank = getBank();
		boolean atmContinue = true;
		while(atmContinue) {
			String card = cardSelection();
			BankAccount ba = bank.getBankAccount(card);
			boolean result = authenticatePIN(ba);	
			while(result) {
				UserAccount ua = ba.getOwner();
				ba = accountSelection(ua, null);
				AccountAction action = accountActionSelection(ua);
				performAccountAction(action,ba);
				if (sessionDone()) {
					atmContinue = false;
					break;
				}
			}
		}
	}
	
	/**
	 * Asks user if they want to make another transaction.
	 * @return Boolean on if user want to end session
	 */
	public static boolean sessionDone() {
		while (true) {
			System.out.println("Another Transaction?");
			System.out.println("Y - Yes");
			System.out.println("N - No");
			try {
				System.out.print("Enter Selection: ");
				String selection = s.next();
				System.out.println();
				switch (selection.toLowerCase()) {
					case "y":
						return false;
					case "n":
						return true;
					default: break;
				}
			}
			catch (InputMismatchException ex) {
				s.next();
			}
			
			System.out.println("Invalid account action selection, Try Again!");
			
		}
	}
	/**
	 * Performs the specified account action.
	 * @param action AccountAction to perform
	 * @param ba BankAccount to perform action on.
	 */
	public static void performAccountAction(AccountAction action, BankAccount ba) {
		switch(action) {
			case CheckBalance:			
				TransactionManager.showBalance(ba).PrintReceipt();
				break;
			case Deposit:
				TransactionManager.deposit(ba, getAmount()).PrintReceipt();
				break;
			case Transfer:
				BankAccount destAccount = accountSelection(ba.getOwner(), ba);
				TransactionManager.transfer(ba, destAccount, getAmount()).PrintReceipt();
				break;
			case Withdraw:
				TransactionManager.withdraw(ba, getAmount()).PrintReceipt();
				break;
			default:
				break;
		}
		System.out.println();
	}

	/** 
	 * Retrieves a dollar amount from the user.
	 * (Restraint) Must be between $10-$2000
	 * @return BigDecimal amount the user entered.
	 */
	public static BigDecimal getAmount() {
		
		BigDecimal min = new BigDecimal("10.00");
		BigDecimal max = new BigDecimal("2000.00");
		
		while(true) {
			try {
				System.out.print("Enter Amount: ");
				BigDecimal amount = s.nextBigDecimal();
				System.out.println();
				if (amount.compareTo(min) != -1 && amount.compareTo(max) != 1) {			
					return amount.setScale(2, RoundingMode.HALF_UP);
				}
			}
			catch (InputMismatchException ex) {
				s.next();
			}
			System.out.println("Must be between $10.00 and $2000.00, Please Try Again!");
		}
	}

	/** 
	 * Retrieves an AccountAction from the user.
	 * @param ua UserAccount to see if transfer action can be made.
	 * @return AccountAction the user selected.
	 */
	public static AccountAction accountActionSelection(UserAccount ua) {
		boolean skipTransfer = ua.GetBankAccounts().size() > 1;
		while(true) {
			System.out.println("********** Account Action Selection ***********");
			System.out.println("****    Please select an account action    ****");
			System.out.println("***********************************************");
			System.out.println();
			System.out.println("1 - Withdraw");
			System.out.println("2 - Deposit");
			System.out.println("3 - Check Balance");
			if (skipTransfer) {
				System.out.println("4 - Transfer");
			}
			System.out.println();
			System.out.print("Enter Account Action Selection : "); 
			try {
				int selection = s.nextInt();
				System.out.println();
				switch (selection) {
					case 1:
						return AccountAction.Withdraw;
					case 2:
						return AccountAction.Deposit;
					case 3:
						return AccountAction.CheckBalance;				
					case 4:
						if (skipTransfer) {
							return AccountAction.Transfer;
						}
				}
			}
			catch (InputMismatchException ex) {
				s.next();
			}
	        System.out.println("Invalid account action selection, Try Again!");
		}
	}

	/**
	 * Retrieves bank account selection from user
	 * @param ua UserAccount to retrieve accounts from.
	 * @param filter BankAccount to filter from selection such as transfer.
	 * @return BankAccount user selected.
	 */
	public static BankAccount accountSelection(UserAccount ua, BankAccount filter) {
		ArrayList<BankAccount> availableAccounts = new ArrayList<BankAccount>();
		for (BankAccount ba : ua.GetBankAccounts()) {
			if (ba != filter) {
				availableAccounts.add(ba);
			}
		}
		while(true) {
			System.out.println("********** Account Selection ***********");
			System.out.println("****    Account Holder:" + ua.getName() + "   ****");
			System.out.println("****************************************");
			System.out.println();
			int index = 0;
			for (BankAccount ba : availableAccounts) {
				index++;
				System.out.println((index) + " - " + ba.toString());
				
			}
			System.out.println();
			System.out.print("Enter Account Selection : ");
			try {
		        int select = s.nextInt();
		        System.out.println();
		        if (select > 0 && select <= index)
		        {
		        	return availableAccounts.get(select - 1);
		        }
			}
			catch (InputMismatchException ex) {
				s.next();
			}
	        System.out.println("Invalid Account selection, Try Again!");	
		}
	}
	
	/**
	 * Retrieves card selection from user.
	 * @return The BankAccount associated with card the user selected.
	 */
	public static String cardSelection() {
		while(true) {
			System.out.println("********** Card Selection ***********");
			System.out.println("****   Please\"Swipe\" a Card    ****");
			System.out.println("*************************************");
			System.out.println();
			System.out.println("1 - John Smith's Card");
			System.out.println("2 - Jane Doe's Card");
			System.out.println("3 - Foo Bar's Card");
			System.out.println();
			System.out.print("Enter Card Selection : ");
			try {
				int selection = s.nextInt();
				System.out.println();
				switch (selection) {
					case 1:
						return "1111-1111-1111-0011";
					case 2:
						return "2222-2222-2222-0022";
					case 3:
						return "3333-3333-3333-0031";
				}
			}
			catch (InputMismatchException ex) {
				s.next();
			}
	        System.out.println("Invalid card selection, Try Again!");		
		}
	}

	/** Retrieves a Bank object.
	 * @return Bank containing pre-populated with user accounts and bank accounts.
	 */
	public static Bank getBank() {
		Bank bank = new Bank();
		
		UserAccount johnSmith = new UserAccount("John Smith", "1111-2222-3333-4444");		
		johnSmith.AddBankAccount(new BankAccount("Checking", "1111-1111-1111-0011", "111111", new BigDecimal("200.00")));
		johnSmith.AddBankAccount(new BankAccount("Savings", "1111-1111-1111-0012", "222222", new BigDecimal("1000.00")));
		johnSmith.AddBankAccount(new BankAccount("Retirement", "1111-1111-1111-0013", "333333", new BigDecimal("50000.00")));
		bank.addUsers(johnSmith);
		
		UserAccount janeDoe = new UserAccount("Jane Doe", "5555-6666-7777-8888");
		janeDoe.AddBankAccount(new BankAccount("Checking", "2222-2222-2222-0021", "111111", new BigDecimal("50.00")));
		janeDoe.AddBankAccount(new BankAccount("Savings", "2222-2222-2222-0022", "222222", new BigDecimal("10.00")));
		janeDoe.AddBankAccount(new BankAccount("LockedAccount", "2222-2222-2222-0023", "333333", new BigDecimal("10.00")));
		janeDoe.GetBankAccounts().get(2).setAccountLock(true);
		bank.addUsers(janeDoe);
		
		UserAccount fooBar = new UserAccount("Foo Bar", "9999-8888-7777-6666");
		fooBar.AddBankAccount(new BankAccount("Checking", "3333-3333-3333-0031", "333333", new BigDecimal("500.00")));
		bank.addUsers(fooBar);
		
		return bank;
	}

	/** 
	 * Requests user for a PIN to verify they are the owner of account.
	 * @param ba BankAccount to authenticate
	 * @return Boolean if an account becomes locked after 3 unsuccessful attempts or account was previously locked.
	 */
	public static boolean authenticatePIN(BankAccount ba)
	{
		PinAuthenticator pinAuth = new PinAuthenticator(ba);
		while(true) {			
			try {
				System.out.print("Please Enter your PIN:");
				try {
					String pin = s.next();
					System.out.println();
					if (pinAuth.checkPin(pin)) {
						System.out.println("PIN Accepted!");
						return true;
					}
				}	
				catch (InputMismatchException ex) {
					s.next();
				}
				System.out.println("Incorrect PIN! Please Try Again!");
			}
			catch (AccountLockException ex) {
				System.out.println("Account has been locked for too many incorrect PIN attempts!");
				break;
			}

		}
		return false;		
	}
}
