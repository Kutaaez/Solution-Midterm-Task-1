package onlinePaymentGateway.transactions;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private static int idCounter = 1;
    private final  int transactionId;
    private final LocalDateTime timestamp;
    private final String paymentType;
    private final String account;
    private final double amount;
    private final String status;
    public Transaction(String paymentType, String account, double amount, String status) {
        this.transactionId = idCounter++;
        this.timestamp = LocalDateTime.now();
        this.paymentType = paymentType;
        this.account = account;
        this.amount = amount;
        this.status = status;
    }
    public int getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String toString() {
        return " \nTransaction ID: " + transactionId +
                "\n Date: " + timestamp +
                "\n Payment Type: " + paymentType +
                "\n Account: " + account +
                "\n Amount: $" + amount +
                "\n Status: " + status;
    }
}
