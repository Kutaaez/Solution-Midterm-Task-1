package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.Coffee;

public class WhippedCreamDecorator extends CoffeeDecorator  {
    private static final double extraCost = 0.60;
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override

    public double getCost() {
        return coffee.getCost() + extraCost;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " A light and fluffy topping for a sweeter, creamier taste.";
    }
}
