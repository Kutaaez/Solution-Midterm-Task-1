package onlinePaymentGateway.adapters;

import onlinePaymentGateway.exceptions.InsufficientFundsException;

import java.util.HashMap;
import java.util.Map;

public class ExternalPaymentAPI {
    private static final Map<String, Double> creditCardBalances = new HashMap<>();
    private static final Map<String, Double> payPalBalances = new HashMap<>();
    private static final Map<String, Double> cryptoWalletBalances = new HashMap<>();


        public static void registerCardCredit(String newCreditCard, double newCreditCardBalance) {
            creditCardBalances.put(newCreditCard, newCreditCardBalance);
        }

        public static void registerPayPal(String newPayPalMail, double newPayPalBalance) {
            payPalBalances.put(newPayPalMail, newPayPalBalance);
        }
        public static void registerCrypto(String newCryptoWallet, double newCryptoBalance) {
            cryptoWalletBalances.put(newCryptoWallet, newCryptoBalance);
        }

    public static boolean proccessCreditCardPayment(String creditCardNumber, double amount) throws InsufficientFundsException {
        if (!creditCardBalances.containsKey(creditCardNumber)) {
            System.out.println(" External API: This credit card not registered.");
            return false;
        }
        double balance = creditCardBalances.get(creditCardNumber);
        if (balance < amount) {
            throw new InsufficientFundsException(" External API: Insufficient funds on credit card. Available: $" + balance);
        }

        creditCardBalances.put(creditCardNumber, balance - amount);
        System.out.println(" External API: Credit card payment approved.");
        System.out.println(" New Balance: $" + creditCardBalances.get(creditCardNumber));
        return true;
    }

    public boolean processPayPalPayment(String payPalMail, double amount) throws InsufficientFundsException {
        if (!payPalBalances.containsKey(payPalMail)) {
            System.out.println(" External API: This PayPal account is not registered.");
            return false;
        }
        double balance = payPalBalances.get(payPalMail);
        if (balance < amount) {
            throw new InsufficientFundsException(" External API: Insufficient funds on paypal account. Available: $" + balance);
        }

        payPalBalances.put(payPalMail, balance - amount);
        System.out.println(" External API: PayPal transaction is Payment approved.");
        System.out.println(" New Balance: $" + payPalBalances.get(payPalMail));
        return true;
    }
    public boolean processCrypto(String cryptoWalletAddress, double amount) throws InsufficientFundsException {
        if (!cryptoWalletBalances.containsKey(cryptoWalletAddress)) {
            System.out.println(" External API:  Crypto wallet is not registered.");
            return false;
        }
        double balance = cryptoWalletBalances.get(cryptoWalletAddress);
        if (balance < amount) {
            throw new InsufficientFundsException(" External API: Insufficient funds on crypto wallet Available: $" + balance);
        }

        cryptoWalletBalances.put(cryptoWalletAddress, balance - amount);
        System.out.println(" External API: Crypto transaction is payment approved.");
        System.out.println(" New Balance: $" + cryptoWalletBalances.get(cryptoWalletAddress));
        return true;
    }


    public double getPayPalBalancec(String payPalMail ){
            return payPalBalances.get(payPalMail);
    }
    public double getCryptoBalances(String cryptoWalletAddress ){
        return cryptoWalletBalances.get(cryptoWalletAddress);
    }
    public double getCreditCardBalances(String creditCardNumber ){
        return creditCardBalances.get(creditCardNumber);
    }

}
