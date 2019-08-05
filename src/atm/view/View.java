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

public class View {
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args)
	{
		Bank bank = getBank();
		
		String card = cardSelection();
		
		BankAccount ba = bank.getBankAccount(card);
		boolean result = authenticatePIN(ba);
		UserAccount ua = ba.getOwner();
		if (result) {
			ba = accountSelection(ua, null);
			AccountAction action = accountActionSelection();
			performAccountAction(action,ba);
		}
	}

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
	}

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

	public static AccountAction accountActionSelection() {
		while(true) {
			System.out.println("########## Account Action Selection ###########");
			System.out.println("Please select an account action");
			System.out.println("###############################################");
			System.out.println();
			System.out.println("1 - Withdraw");
			System.out.println("2 - Deposit");
			System.out.println("3 - Transfer");
			System.out.println("4 - Check Balance");
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
						return AccountAction.Transfer;
					case 4:
						return AccountAction.CheckBalance;
				}
			}
			catch (InputMismatchException ex) {
				s.next();
			}
	        System.out.println("Invalid account action selection, Try Again!");
		}
	}

	public static BankAccount accountSelection(UserAccount ua, BankAccount filter) {
		ArrayList<BankAccount> availableAccounts = new ArrayList<BankAccount>();
		for (BankAccount ba : ua.GetBankAccounts()) {
			if (ba != filter) {
				availableAccounts.add(ba);
			}
		}
		while(true) {
			System.out.println("########## Account Selection ###########");
			System.out.println("Account Holder:" + ua.getName());
			System.out.println("########################################");
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
	
	public static String cardSelection() {
		while(true) {
			System.out.println("########## Card Selection ###########");
			System.out.println();
			System.out.println("1 - CARD 1");
			System.out.println("2 - CARD 2");
			System.out.println("3 - CARD 3");
			System.out.println();
			System.out.print("Enter Card Selection : ");
			try {
				int selection = s.nextInt();
				System.out.println();
				switch (selection) {
					case 1:
						return "1111-1111-1111-0011";
					case 2:
						return "1111-1111-1111-0012";
					case 3:
						return "2222-2222-2222-0021";
				}
			}
			catch (InputMismatchException ex) {
				s.next();
			}
	        System.out.println("Invalid card selection, Try Again!");		
		}
	}

	public static Bank getBank() {
		Bank bank = new Bank();
		
		UserAccount johnSmith = new UserAccount("John Smith", "1111-2222-3333-4444");		
		johnSmith.AddBankAccount(new BankAccount("Checking", "1111-1111-1111-0011", "1111", new BigDecimal("200.00")));
		johnSmith.AddBankAccount(new BankAccount("Savings", "1111-1111-1111-0012", "2222", new BigDecimal("1000.00")));
		johnSmith.AddBankAccount(new BankAccount("Retirement", "1111-1111-1111-0013", "3333", new BigDecimal("50000.00")));
		bank.addUsers(johnSmith);
		
		UserAccount janeDoe = new UserAccount("Jane Doe", "5555-6666-7777-8888");
		janeDoe.AddBankAccount(new BankAccount("Checking", "2222-2222-2222-0021", "1111", new BigDecimal("50.00")));
		janeDoe.AddBankAccount(new BankAccount("Savings", "2222-2222-2222-0022", "5555", new BigDecimal("10.00")));
		bank.addUsers(janeDoe);
		
		return bank;
	}

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
