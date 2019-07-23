package atm.model;
import java.util.*;

public class Bank {
	
	//ArrayList for the users of the bank "UserAccounts" and for the different types
	//of accounts for each of those users "BankAccounts"
	private ArrayList<UserAccount> UserAccounts = new ArrayList<UserAccount>();
	private ArrayList<BankAccount> BankAccounts = new ArrayList<BankAccount>();
	
	//Gets users account when signing in
	public UserAccount getUserAccount(String ID) {
		
		//This user account returned when found
		UserAccount foundUser = new UserAccount();
		
		return(foundUser);
	}
	
	//Gets users different accounts within the bank
	public BankAccount getUserBankAccounts() {
		
		//This bank account returned when found with the user
		BankAccount foundBankAccount = new BankAccount();
		
		return(foundBankAccount);
	}

}
