package atm.controller;

import java.lang.*;

public class AccountVerification {
	private String accountNo;
	private String pin;
	private Boolean cardLock = false; // default to not locked
	private String test; //test purposes only
	
	
	public AccountVerification(String accountNo, String pin) {
		this.accountNo = accountNo;
		this.pin = pin;
	}
	
	public void setTestPin(String input) {
		test = input;
	}
	
	public void authenticate() {
		int counter = 0;
		do {
			if(!cardLock) {
				System.out.println("Enter your PIN: ");
				if(pin.contentEquals(test)) {
					System.out.println("Welcome");
					break;
				}
				else {
					System.out.println("Incorrect PIN");
				}
				counter++;
			}
			else {
				System.out.println("Card has been locked. Please contact you bank.");
				break;
			}
		} while(counter < 3);
		
		if(counter == 3) {
			System.out.println("Attempts exceeded limit, locking now");
			cardLock = true;
		}
	}
	public Boolean checkLockStatus() {
		return cardLock;
	}
	
}
