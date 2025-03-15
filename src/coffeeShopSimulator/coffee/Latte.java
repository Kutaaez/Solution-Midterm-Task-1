package coffeeShopSimulator.coffee;

public class Latte implements Coffee{
    @Override
    public double getCost() {
        return 4.50;
    }

    @Override
    public String getDescription() {
        return "Latte \nSmooth and creamy coffee with a generous amount of steamed milk.";
    }
}
