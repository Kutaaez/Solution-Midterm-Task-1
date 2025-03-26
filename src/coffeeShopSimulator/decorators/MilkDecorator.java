package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.Coffee;

public class MilkDecorator extends CoffeeDecorator {

    private double milkPrice;

    public MilkDecorator(Coffee coffee) {
        super(coffee);
        this.milkPrice = 0.50;
    }

    public double getMilkPrice() {
        return milkPrice;
    }

    public void setMilkPrice(double milkPrice) {
        this.milkPrice = milkPrice;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "\n + Adds a smooth and creamy texture to your coffee. (MilkDecorator)\n";
    }
}