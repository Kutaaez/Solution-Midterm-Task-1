package onlinePaymentGateway.adapters;

import onlinePaymentGateway.exceptions.InsufficientFundsException;

public class CreditCardAdapter {
    //create this addapter for validate card number.
    private final ExternalPaymentAPI externalAPI;

    public CreditCardAdapter() {
        this.externalAPI =new ExternalPaymentAPI();;
    }
    public boolean validateCard(String cardNumber) {
        return cardNumber.startsWith("4") || cardNumber.startsWith("5");
    }
    public void registerCard(String cardNumber, double initialBalance) {
        externalAPI.registedCardCredit(cardNumber, initialBalance);
    }
    public boolean processPayment(String cardNumber, double amount) throws InsufficientFundsException {
        return externalAPI.proccessCreditCardPayment(cardNumber, amount);
    }
}
