package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.ICoffee;

public class MilkDecorator extends CoffeeDecorator{
    private static final double extraCost = 0.50;
    public MilkDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + extraCost;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + "\n +  Adds a smooth and creamy texture to your coffee.(MilkDecorator)";
    }
}
