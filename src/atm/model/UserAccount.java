package atm.model;

import java.util.ArrayList;

public class UserAccount {

	private String ID;
	private String Name;
	private ArrayList<BankAccount> BankAccounts;
	
	public UserAccount() {
		this("", "");
	}
	
	public UserAccount(String id, String name) {
		this.ID = id;
		this.Name = name;
		this.BankAccounts = new ArrayList<BankAccount>();
	}

	public String getID() {
		return ID;
	}

	public String getName() {
		return Name;
	}
	
	public void AddBankAccount(BankAccount ba) {
		ba.setOwner(this);
		BankAccounts.add(ba);
	}
	
	public void RemoveBankAccount(BankAccount ba) {
		BankAccounts.remove(ba);
		ba.setOwner(null);
	}
	
	public ArrayList<BankAccount> GetBankAccounts() {
		return BankAccounts;
	}
	
}
