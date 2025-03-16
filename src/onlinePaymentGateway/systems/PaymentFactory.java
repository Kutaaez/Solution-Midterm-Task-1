package onlinePaymentGateway.systems;

import onlinePaymentGateway.exceptions.InvalidDataException;

import java.util.Locale;
import java.util.Scanner;

public class PaymentFactory {
    public static IPaymentMethod createPayment(String typeOfWallet, Scanner scanner){
        System.out.print("Please enter your payment details: ");
        String input = scanner.nextLine();

        try {
            //shorted swtich case operator, in java 14 we get this method.
            //i think this better and beautiful?
            return switch (typeOfWallet.toLowerCase(Locale.ROOT)) {
                case "paypal", "пейпал" -> new PayPalPayment(input);
                case "crypto", "крипто" -> new CryptoPayment(input) ;
                case "creditcard", "credit card", "кредитная карта", "кредитка" -> new CreditCardPayment(input);
                default -> throw new InvalidDataException("Unknown payment method.");
            };
        } catch (InvalidDataException e) {
            System.out.println("Invalid input: " + e.getMessage());
            System.out.println("Please try again.");
            return null;
        }
        }
    }
