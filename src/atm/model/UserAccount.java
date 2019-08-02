package atm.model;

import java.util.ArrayList;

/**
 * UserAccount class that contains user account information (name, id, bank accounts) and methods to associate bank accounts ownership. 
 * @author Chris Carson
 *
 */
public class UserAccount {
	
	private String ID;
	private String Name;
	private ArrayList<BankAccount> BankAccounts;
	
	
	/**
	 * Default constructor for UserAccount.
	 * @param id Identifier for the UserAccount.
	 * @param name Name of the UserAccount.
	 */
	public UserAccount(String id, String name) {
		this.ID = id;
		this.Name = name;
		this.BankAccounts = new ArrayList<BankAccount>();
	}

	/**
	 * Retrieves the UserAccount ID.
	 * @return String of the ID of the UserAccount.
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Retrieves the UserAccount Name.
	 * @return String of the Name of the UserAccount.
	 */
	public String getName() {
		return Name;
	}
	
	/**
	 * Adds a BankAccount to BankAccounts list, and sets ownership of the BankAccount to this UserAccount.
	 * @see atm.model.BankAccount
	 * @param ba BankAccount to add and associate to this UserAccount
	 */
	public void AddBankAccount(BankAccount ba) {
		ba.setOwner(this);
		BankAccounts.add(ba);
	}
	
	/**
	 * Removes a BankAccount from BankAccounts list, and removes ownership of the BankAccount.
	 * @see atm.model.BankAccount
	 * @param ba BankAccount to remove and dissociate from this UserAccount
	 */
	public void RemoveBankAccount(BankAccount ba) {
		BankAccounts.remove(ba);
		ba.setOwner(null);
	}
	
	/**
	 * Retrieves the UserAccount BankAccounts.
	 * @return ArrayList of BankAccounts associated with this UserAccount.
	 */
	public ArrayList<BankAccount> GetBankAccounts() {
		return BankAccounts;
	}
	
}
