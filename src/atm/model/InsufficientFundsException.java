package atm.model;

public class InsufficientFundsException extends Exception{
	public InsufficientFundsException(){
		super("Insufficient Funds!");
	}
}
