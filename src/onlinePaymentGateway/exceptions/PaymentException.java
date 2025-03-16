package onlinePaymentGateway.exceptions;

public class PaymentException extends Exception {
    //all exeptions another side
    public PaymentException(String message) {
        super(message);
    }
}