package onlinePaymentGateway.exceptions;

public class InsufficientFundsException extends Exception {
    //don't have enough funds in balances.
    public InsufficientFundsException(String message) {
        super(message);
    }
}