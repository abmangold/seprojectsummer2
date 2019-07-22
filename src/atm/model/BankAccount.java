package atm.model;

public class BankAccount extends AbstractModel{
	private double Balance;
	private String Name;
	private String ID;
	private String PIN;
	public BankAccount(String name, 
					   String ID, 
					   String PIN, 
					   double balance) {
		this.setName(Name);
		this.setID(ID);
		this.setPIN(PIN);
		this.setBalance(balance);		
	}
	public String getPIN() {
		return PIN;
	}
	private void setPIN(String PIN) {
		this.PIN = PIN;
	}
	public String getID() {
		return ID;
	}
	private void setID(String ID) {
		this.ID = ID;
	}
	public String getName() {
		return Name;
	}
	private void setName(String name) {
		this.Name = name;
	}
	public double getBalance() {
		return Balance;
	}
	private void setBalance(double balance) {
		this.Balance = balance;
	}
	
	public double withdraw(double amount) throws Exception {
		double newBalance = Balance;
		newBalance -= amount;
		if (newBalance < 0) throw new Exception("OverDraw");
		// Add Model Event
		Balance = newBalance;
		// Notify Changed
		return Balance;
	}
	
	public double deposit(double amount) {
		double newBalance = Balance;
		newBalance += amount;
		// Add Model Event
		Balance = newBalance;
		// Notify Changed
		return Balance;
	}
}
