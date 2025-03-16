package onlinePaymentGateway.systems;

import onlinePaymentGateway.adapters.CreditCardAdapter;
import onlinePaymentGateway.adapters.PayPalAdapter;
import onlinePaymentGateway.exceptions.InsufficientFundsException;
import onlinePaymentGateway.exceptions.InvalidDataException;
import onlinePaymentGateway.transactions.Transaction;
import onlinePaymentGateway.transactions.TransactionLogger;

import java.util.Random;

public class PayPalPayment implements IPaymentMethod {
    private final PayPalAdapter payPalAdapter;
    private final String payPalMail;
    private double payPalBalance;

    public PayPalPayment(String payPalMail) throws InvalidDataException {
        this.payPalAdapter = new PayPalAdapter();
        this.payPalMail = payPalMail;
        if (!payPalAdapter.validateAddress(payPalMail)) {
            throw new InvalidDataException(" Invalid mail address ! Please try agaim.");
        }
        this.payPalBalance = new Random().nextInt(900) + 100;
        payPalAdapter.registerAccount(payPalMail,payPalBalance);

    }

    public void setPayPalBalance(double payPalBalance) {
        this.payPalBalance = payPalBalance;
    }

    public double getPayPalBalance() {
        return payPalBalance;
    }

    public String getPayPalMail() {
        return payPalMail;
    }

    public void printInfo() {
        System.out.println("Account mail: " + getPayPalMail());
        System.out.println("Balance: $" + getPayPalBalance());
    }

    public void processPayment(double amount)  {

      try{
          boolean processSuccess =payPalAdapter.processPayment(getPayPalMail(), amount);


        if (processSuccess) {

            System.out.println("Account Payment Approved: $" + amount);
            setPayPalBalance(payPalAdapter.newBalance(getPayPalMail()));
            printInfo();
        }
      else {
            System.out.println("Account Payment Failed: Don't have enough funds. Transaction: " + amount);
            printInfo();
        }
          String status = processSuccess ? "COMPLETED" : "FAILED";
          Transaction transaction = new Transaction("Crypto transfer", getPayPalMail(), amount, status);
          TransactionLogger.logTransactions(transaction);

      }

      catch (InsufficientFundsException  e ){
          System.out.println("❌ Payment Failed: " + e.getMessage());
          TransactionLogger.logTransactions(new Transaction("PayPal", getPayPalMail(), amount, "FAILED"));

      }
      }
    }
