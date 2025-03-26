package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.Coffee;

public class CaramelDecorator extends  CoffeeDecorator{

    double caramelPrice;

    public CaramelDecorator(Coffee coffee) {
        super(coffee);
        this.caramelPrice = 0.70;
    }

    @Override
    public double getCost() {
        return coffee.getCost() + caramelPrice;
    }

    public double getCaramelPrice() {
        return caramelPrice;
    }

    public void setCaramelPrice(double caramelPrice) {
        this.caramelPrice = caramelPrice;
    }

    @Override
    public String getDescription() {
        return super.getDescription() +  "\n + A sweet caramel touch for a richer flavor. (CaramelDecorator)\n";
    }
}
