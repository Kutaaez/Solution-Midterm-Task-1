package onlinePaymentGateway.systems;

import onlinePaymentGateway.exceptions.InsufficientFundsException;

public interface IPaymentMethod {
    void processPayment(double amount) throws InsufficientFundsException;
}
