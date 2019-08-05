package atm.model;
import java.util.*;

public class Bank {
	
	//ArrayList for the users of the bank "UserAccounts" and for the different types
	//of accounts for each of those users "BankAccounts"
	private ArrayList<UserAccount> UserAccounts = new ArrayList<UserAccount>();
	private ArrayList<BankAccount> BankAccounts = new ArrayList<BankAccount>();
	
	//Gets users account when signing in
	public UserAccount getUserAccount(String ID) {
		for (UserAccount ua : UserAccounts)	
		{
			if (ua.getID().equals(ID))
			{
				return ua;
			}
		}
		return null;
	}
	
	//Gets users different accounts within the bank
	public ArrayList<BankAccount> getUserBankAccounts(String ID) {
		UserAccount ua = getUserAccount(ID);
		if (ua != null)
		{
			return ua.GetBankAccounts();
		}
		
		return new ArrayList<BankAccount>();
	}
	
	public BankAccount getBankAccount(String ID) {
		for (BankAccount ba : BankAccounts)	
		{
			if (ba.getID().equals(ID))
			{
				return ba;
			}
		}
		return null;
	}
	
	//Adding users to UserAccounts List
	public void addUsers(UserAccount newUser) {
		if (!UserAccounts.contains(newUser))
		{
			UserAccounts.add(newUser);
			for (BankAccount ba : newUser.GetBankAccounts())
			{
				addBankAccount(ba);
			}
		}
	}
	
	//Adding bank account to BankAccount List
	public void addBankAccount(BankAccount newAccount) {
		if (!BankAccounts.contains(newAccount))
		{
			BankAccounts.add(newAccount);
		}
	}

}
