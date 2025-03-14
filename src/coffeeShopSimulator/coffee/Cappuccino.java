package coffeeShopSimulator.coffee;

public class Cappuccino implements Coffee{
    @Override
    public double getCost() {
        return 3.00;
    }

    @Override
    public String getDescription() {
        return "A perfect balance of espresso, steamed milk, and frothy milk foam.";
    }
}
