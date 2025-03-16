package onlinePaymentGateway.adapters;

import onlinePaymentGateway.exceptions.InsufficientFundsException;

public class PayPalAdapter {
    //create this addapter for validate mail.
    private final ExternalPaymentAPI externalAPI;

    public PayPalAdapter() {
        this.externalAPI =new ExternalPaymentAPI();;
    }

    public boolean validateAddress(String payPalMail) {
        return payPalMail.contains("@") && payPalMail.contains(".");
    }
    public boolean processPayment(String payPalMail, double amount) throws InsufficientFundsException {
       return externalAPI.processPayPalPayment(payPalMail,amount);
    }
    public void registerAccount(String payPalMail, double initialBalance) {
        externalAPI.registedPayPal(payPalMail, initialBalance);
    }
}
