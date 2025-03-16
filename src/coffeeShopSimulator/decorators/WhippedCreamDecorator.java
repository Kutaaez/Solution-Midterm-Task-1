package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.ICoffee;

public class WhippedCreamDecorator extends CoffeeDecorator  {
    private static final double extraCost = 0.60;
    public WhippedCreamDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override

    public double getCost() {
        return coffee.getCost() + extraCost;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + "\n + A light and fluffy topping for a sweeter, creamier taste.(WhippedCream)";
    }
}
