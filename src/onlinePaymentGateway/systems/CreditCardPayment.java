package onlinePaymentGateway.systems;

import onlinePaymentGateway.adapters.CreditCardAdapter;
import onlinePaymentGateway.adapters.ExternalPaymentAPI;
import onlinePaymentGateway.exceptions.InsufficientFundsException;
import onlinePaymentGateway.exceptions.InvalidDataException;

import java.util.Random;

public class CreditCardPayment implements IPaymentMethod {
    private final CreditCardAdapter creditAdapter;
    private final String creditCardNumber;
    private double creditCardBalance;
    public CreditCardPayment(String cardNumber) throws InvalidDataException {
        this.creditAdapter = new CreditCardAdapter();
        if (!creditAdapter.validateCard(cardNumber)) {
            throw new InvalidDataException(" Invalid credit card number! Card must start with 4 or 5.");
        }
        this.creditCardNumber = cardNumber;
        this.creditCardBalance = new Random().nextInt(900) + 100;
        creditAdapter.registerCard(creditCardNumber,creditCardBalance);
    }
    public void printCardInfo() {
        System.out.println("Credit Card: " + creditCardNumber);
        System.out.println("Balance: $" + creditCardBalance);
    }
    public void processPayment(double amount) throws InsufficientFundsException {
        boolean processSuccess =creditAdapter.processPayment(creditCardNumber, amount);
        if (processSuccess) {
            System.out.println("Credit Card Payment Approved: $" + amount);
            System.out.println("Balance: $" + creditCardBalance);
        } else {
            System.out.println("Credit Card Payment Failed: Don't have enough funds. Transaction: " + amount);
            System.out.println("Balance: $" + creditCardBalance);

        }
    }


}
