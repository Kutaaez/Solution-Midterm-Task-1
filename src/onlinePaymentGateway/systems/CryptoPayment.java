package onlinePaymentGateway.systems;

import onlinePaymentGateway.adapters.CreditCardAdapter;
import onlinePaymentGateway.adapters.CryptoAdapter;
import onlinePaymentGateway.exceptions.InsufficientFundsException;
import onlinePaymentGateway.exceptions.InvalidDataException;
import onlinePaymentGateway.transactions.Transaction;
import onlinePaymentGateway.transactions.TransactionLogger;

import java.util.Random;

public class CryptoPayment implements IPaymentMethod {
    private final CryptoAdapter cryptoAdapter;
    private final String cryptoWalletNumber;
    private double cryptoWalletBalance;

    public String getCryptoWalletNumber() {
        return cryptoWalletNumber;
    }

    public double getCryptoWalletBalance() {
        return cryptoWalletBalance;
    }

    public void setCryptoWalletBalance(double cryptoWalletBalance) {
        this.cryptoWalletBalance = cryptoWalletBalance;
    }

    public CryptoPayment(String cryptoWalletNumber) throws InvalidDataException {
        this.cryptoAdapter= new CryptoAdapter();
        if (!cryptoAdapter.validateWallet(cryptoWalletNumber)) {
            throw new InvalidDataException(" Invalid wallet address! ");
        }
        this.cryptoWalletNumber = cryptoWalletNumber;
        this.cryptoWalletBalance = new Random().nextInt(900) + 100;
        cryptoAdapter.registerWallet(cryptoWalletNumber,cryptoWalletBalance);
    }
    public void printInfo() {
        System.out.println("Crypto wallet address: " + getCryptoWalletNumber());
        System.out.println("Balance: $" + getCryptoWalletBalance());
    }

    public void processPayment(double amount)  {
        try{
            boolean processSuccess =cryptoAdapter.processPayment(getCryptoWalletNumber(), amount);

            String status = processSuccess ? "COMPLETED" : "FAILED";
            Transaction transaction = new Transaction("Crypto transfer", getCryptoWalletNumber(), amount, status);
            TransactionLogger.logTransactions(transaction);

            if (processSuccess) {

                System.out.println("Account Payment Approved: $" + amount);
                setCryptoWalletBalance(cryptoAdapter.newBalance(getCryptoWalletNumber()));
                printInfo();
            }
            else {
                System.out.println("Account Payment Failed: Don't have enough funds. Transaction: " + amount);
                printInfo();
            }
        }
        catch (InsufficientFundsException  e ){
            System.out.println("❌ Payment Failed: " + e.getMessage());
            TransactionLogger.logTransactions(new Transaction("Crypto transfer", getCryptoWalletNumber(), amount, "FAILED"));

        }
    }
    }


