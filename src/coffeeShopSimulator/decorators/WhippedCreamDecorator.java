package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.Coffee;


public class WhippedCreamDecorator extends CoffeeDecorator {

    private double whippedCreamPrice;

    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
        this.whippedCreamPrice = 0.60;
    }

    public double getWhippedCreamPrice() {
        return whippedCreamPrice;
    }

    public void setWhippedCreamPrice(double whippedCreamPrice) {
        this.whippedCreamPrice = whippedCreamPrice;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "\n + A light and fluffy topping for a sweeter, creamier taste. (WhippedCreamDecorator)\n";
    }
}
