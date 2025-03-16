package onlinePaymentGateway;

import coffeeShopSimulator.app.CoffeeOrderTerminal;
import onlinePaymentGateway.main.PaymentApp;

public class Main {
    public static void main(String[] args) {
        PaymentApp app = new PaymentApp();
        app.start();
    }

}
