package atm.model;

public class AccountLockException extends Exception{
	AccountLockException(){
		super("Account is Locked!");
	}
}
