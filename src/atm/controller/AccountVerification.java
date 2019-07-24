package atm.controller;

import java.lang.*;
import java.math.BigDecimal;

import atm.model.BankAccount;

public class AccountVerification {
	BankAccount ba = new BankAccount("Checking", "123456", "12345", "1111", new BigDecimal("200.00"));
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
				if(pin.contentEquals(ba.getPIN())) {
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
