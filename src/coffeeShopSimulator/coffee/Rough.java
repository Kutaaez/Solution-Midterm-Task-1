package coffeeShopSimulator.coffee;

public class Rough implements ICoffee {
    @Override
    public double getCost() {
        return 2.50;
    }

    @Override
    public String getDescription() {
        return "Rough \nA bold and unfiltered coffee with a strong, raw taste.";
    }
}
