package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.Coffee;

public class ChocolateDecorator extends CoffeeDecorator{
    private static final double extraCost = 0.80;

    public ChocolateDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + extraCost;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + "\n +  A delightful chocolatey twist to enhance your drink.(ChocolateDecorator)";
    }
}
