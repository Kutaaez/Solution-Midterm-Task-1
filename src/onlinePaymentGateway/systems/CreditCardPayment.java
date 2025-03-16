package onlinePaymentGateway.systems;

import onlinePaymentGateway.adapters.CreditCardAdapter;
import onlinePaymentGateway.adapters.ExternalPaymentAPI;
import onlinePaymentGateway.exceptions.InsufficientFundsException;
import onlinePaymentGateway.exceptions.InvalidDataException;
import onlinePaymentGateway.transactions.Transaction;
import onlinePaymentGateway.transactions.TransactionLogger;

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
    public void printInfo() {
        System.out.println("Credit Card: " + getCreditCardNumber());
        System.out.println("Balance: $" + getCreditCardBalance());
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public double getCreditCardBalance() {
        return creditCardBalance;
    }

    public void setCreditCardBalance(double creditCardBalance) {
        this.creditCardBalance = creditCardBalance;
    }

    public void processPayment(double amount) throws InsufficientFundsException {
        try{
            boolean processSuccess =creditAdapter.processPayment(getCreditCardNumber(), amount);
            String status = processSuccess ? "COMPLETED" : "FAILED";
            Transaction transaction = new Transaction("Credit Card", getCreditCardNumber(), amount, status);
            TransactionLogger.logTransactions(transaction);
            if (processSuccess) {

                System.out.println("Account Payment Approved: $" + amount);
                setCreditCardBalance(creditAdapter.newBalance(getCreditCardNumber()));
                printInfo();}
            else {
                System.out.println("Account Payment Failed: Don't have enough funds. Transaction: " + amount);
                printInfo();
            }
        }
        catch (InsufficientFundsException  e ){
            System.out.println("❌ Payment Failed: " + e.getMessage());
            TransactionLogger.logTransactions(new Transaction("Credit Card", getCreditCardNumber(), amount, "FAILED"));
        }
    }


}
