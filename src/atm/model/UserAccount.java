package atm.model;

public class UserAccount {

	private String ID;
	private String Name;
	
	public UserAccount() {
		this.ID = "";
		this.Name = "";
	}
	
	public UserAccount(String id, String name) {
		this.ID = id;
		this.Name = name;
	}

	public String getID() {
		return ID;
	}

	public String getName() {
		return Name;
	}
	
}
