package coffeeShopSimulator.coffee;

public class Rough implements  Coffee{
    @Override
    public double getCost() {
        return 2.50;
    }

    @Override
    public String getDescription() {
        return "A bold and unfiltered coffee with a strong, raw taste.";
    }
}
