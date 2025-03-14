package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.Coffee;
//creating class coffeedecorator. It's class one of develop pattern. We can oborachivat new extra information for class coffee.
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    public CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }

}
