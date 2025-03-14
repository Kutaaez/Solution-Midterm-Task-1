package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.Coffee;

public class MilkDecorator extends CoffeeDecorator{
    private static final double extraCost = 0.50;
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + extraCost;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " Adds a smooth and creamy texture to your coffee.";
    }
}
