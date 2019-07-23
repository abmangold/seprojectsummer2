package atm.model;

import java.util.List;

public class Session {

	private String ID;
	private List<String> Events;
	private UserAccount userAccount;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public List<String> getEvents() {
		return Events;
	}
	public void setEvents(List<String> events) {
		Events = events;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}	
}
