package onlinePaymentGateway.transactions;

import java.util.ArrayList;
import java.util.List;

public  class TransactionLogger {
    private static final List<Transaction> transactions = new ArrayList<>();
    public static void logTransactions(Transaction transaction){
        transactions.add(transaction);
        System.out.println(transaction.getTransactionId() + " id transaction logged.");
    }
    public static void printTransactionHistory() {
        System.out.println("\n Transaction History:");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
                System.out.println("-----------------------------------");
            }
        }
    }
    public static List<Transaction> getTransactions() {
        return transactions;
    }
}
