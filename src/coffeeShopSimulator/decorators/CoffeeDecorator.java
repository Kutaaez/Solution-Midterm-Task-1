package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.Coffee;
//creating class coffeedecorator. It's class one of develop pattern. We can oborachivat new extra information for class coffee.
public abstract class CoffeeDecorator extends Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public double getCost() {
        return coffee.getCost();
    }
    public String getDescription() {
        return coffee.getDescription();
    }
}
