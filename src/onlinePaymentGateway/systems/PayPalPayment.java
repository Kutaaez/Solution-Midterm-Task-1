package onlinePaymentGateway.systems;

import onlinePaymentGateway.adapters.CreditCardAdapter;
import onlinePaymentGateway.adapters.PayPalAdapter;
import onlinePaymentGateway.exceptions.InsufficientFundsException;
import onlinePaymentGateway.exceptions.InvalidDataException;

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

    public void printAccountInfo() {
        System.out.println("Account mail: " + payPalMail);
        System.out.println("Balance: $" + payPalBalance);
    }

    public void processPayment(double amount)  {
      try{
          boolean processSuccess =payPalAdapter.processPayment(payPalMail, amount);

        if (processSuccess) {
            System.out.println("Account Payment Approved: $" + amount);
            System.out.println("Balance: $" + payPalBalance);
        }
      else {
            System.out.println("Account Payment Failed: Don't have enough funds. Transaction: " + amount);
            System.out.println("Balance: $" + payPalBalance);

        }
      }
      catch (InsufficientFundsException  e ){
          System.out.println("❌ Payment Failed: " + e.getMessage());
      }
      }
    }
}
