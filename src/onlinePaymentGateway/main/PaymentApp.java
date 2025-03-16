package onlinePaymentGateway.main;

import onlinePaymentGateway.exceptions.InsufficientFundsException;
import onlinePaymentGateway.systems.IPaymentMethod;
import onlinePaymentGateway.systems.PaymentFactory; //Fabric
import onlinePaymentGateway.transactions.TransactionLogger;

import java.util.Locale;
import java.util.Scanner;

public class PaymentApp {
    public void start() {
        Scanner scanner = new Scanner(System.in);
        IPaymentMethod paymentMethod = null;

        while (true) {
            try {
                if (paymentMethod == null) {
                    System.out.print("Choose a payment method (PayPal, Crypto, CreditCard) or type 'exit' to quit: ");
                    String paymentType = scanner.nextLine().toLowerCase(Locale.ROOT);

                    if (paymentType.equals("exit")) {
                        break;
                    }

                    paymentMethod = PaymentFactory.createPayment(paymentType, scanner); //Fabric

                    if (paymentMethod == null) {
                        System.out.println("Invalid payment method. Please try again.");
                        continue;
                    }

                    paymentMethod.printInfo();
                }

                System.out.print("Enter a command (pay, balance, transaction, change, exit): ");
                String command = scanner.nextLine().toLowerCase(Locale.ROOT);

                switch (command) {
                    case "exit":
                        return;
                    case "transaction":
                        TransactionLogger.printTransactionHistory();
                        break;
                    case "pay":
                        System.out.print("Enter the payment amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        paymentMethod.processPayment(amount); //Adapter
                        break;
                    case "balance":
                        paymentMethod.printInfo();
                        break;
                    case "change":
                        System.out.print("Choose a new payment method (PayPal, Crypto, CreditCard): ");
                        String newPaymentType = scanner.nextLine().toLowerCase(Locale.ROOT);
                        paymentMethod = PaymentFactory.createPayment(newPaymentType, scanner); // Fabric
                        paymentMethod.printInfo();
                    default:
                        System.out.println("Unknown command. Please enter 'pay', 'transaction', 'balance', or 'exit'.");
                }
            } catch (InsufficientFundsException e) {
                System.out.println("Payment Failed: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
