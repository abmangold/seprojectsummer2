package atm.controller;

import java.math.BigDecimal;

import atm.model.BankAccount;

public class PinAuthenticator {
	BankAccount ba = new BankAccount("Checking", "1234", "1111", new BigDecimal("200.00")); // Only here for testing purposes, otherwise the object would have existing parameters already.
	private String pin;
	//private String test = ba.getPIN();
	private int attempts = 3;
	private Boolean lock = false;
	
	public PinAuthenticator(String PIN) {
		this.pin = PIN;
	}
	
	public Boolean checkLockStatus() {
		return lock;
	}
	
	
	public void checkPin() {
		do {
			try {
				if(pin.equals(ba.getPIN())) {
					System.out.println("Welcome"); // Msg for clearance testing 
					break;
				}
			}
			catch (Exception e) {
				System.out.println("Incorrect PIN. Try again."); // Msg for fail testing
			}
			attempts--;
		}while(attempts > 0);
		
		if(attempts == 0) {
			System.out.println("Exceeded attempts, locking now.");
			lock = true;
		}
	}
}
