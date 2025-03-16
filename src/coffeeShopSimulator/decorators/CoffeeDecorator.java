package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.ICoffee;
//creating class coffeedecorator. It's class one of develop pattern. We can oborachivat new extra information for class coffee.
public abstract class CoffeeDecorator implements ICoffee {
    protected ICoffee coffee;
    public CoffeeDecorator(ICoffee coffee){
        this.coffee = coffee;
    }

}
