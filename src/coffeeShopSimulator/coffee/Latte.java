package coffeeShopSimulator.coffee;

public class Latte implements Coffee{
    @Override
    public double getCost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Smooth and creamy coffee with a generous amount of steamed milk.";
    }
}
