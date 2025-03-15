package coffeeShopSimulator.coffee;

public class Cappuccino implements Coffee{
    @Override
    public double getCost() {
        return 3.00;
    }

    @Override
    public String getDescription() {
        return "Cappucino \nA perfect balance of espresso, steamed milk, and frothy milk foam.";
    }
}
