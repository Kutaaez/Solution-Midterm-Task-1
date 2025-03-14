package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.Coffee;

public class CaramelDecorator extends  CoffeeDecorator{
    private static final double extraCost = 0.70;

    public CaramelDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + extraCost;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() +  " A sweet caramel touch for a richer flavor.";
    }
}
