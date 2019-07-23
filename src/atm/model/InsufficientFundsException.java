package atm.model;

public class InsufficientFundsException extends Exception{
	InsufficientFundsException(){
		super("Insufficient Funds!");
	}
}
