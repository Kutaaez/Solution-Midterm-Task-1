package coffeeShopSimulator.coffee;

public class Espresso implements ICoffee {
    @Override
    public double getCost() {
        return 3.00;
    }

    @Override
    public String getDescription() {
        return "Espresso \nA strong and rich coffee shot with an intense flavor.";
    }
}
