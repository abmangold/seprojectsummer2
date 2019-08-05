package atm.model;

public class AccountLockException extends Exception{
	public AccountLockException(){
		super("Account is Locked!");
	}
}
