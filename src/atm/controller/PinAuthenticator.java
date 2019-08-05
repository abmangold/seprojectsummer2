package atm.controller;

import java.math.BigDecimal;

import atm.model.AccountLockException;
import atm.model.BankAccount;

public class PinAuthenticator {
	BankAccount ba;
	private int attempts;
	
	public PinAuthenticator(BankAccount ba) {
		this.ba = ba;
		this.attempts = 3;
	}
		
	
	public boolean checkPin(String PINtoValidate) throws AccountLockException {
			if(PINtoValidate.equals(ba.getPIN())) {
				return true;
			}

			attempts--;
		
		if(attempts == 0) {
			ba.setAccountLock(true);
			throw new AccountLockException();
		}
		return false;
	}
}
