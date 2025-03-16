package onlinePaymentGateway.adapters;

import onlinePaymentGateway.exceptions.InsufficientFundsException;

public class CryptoAdapter {
    private final ExternalPaymentAPI externalAPI;
    public CryptoAdapter() {
        this.externalAPI =new ExternalPaymentAPI();;
    }
    public boolean validateWallet(String walletAddress){
        return walletAddress.startsWith("0x") || walletAddress.startsWith("bc1");
    }
    public boolean processPayment(String cryptoWalletAddress, double amount) throws InsufficientFundsException {
        return externalAPI.processCrypto(cryptoWalletAddress,amount);
    }
    public void registerWallet(String cryptoWalletAddress, double initialBalance) {
        externalAPI.registerCrypto(cryptoWalletAddress, initialBalance);
    }
    public double newBalance(String cryptoWalletAddress){
        return externalAPI.getCryptoBalances(cryptoWalletAddress);
    }
}
