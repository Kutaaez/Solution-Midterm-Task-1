package coffeeShopSimulator.decorators;

import coffeeShopSimulator.coffee.Coffee;

public class ChocolateDecorator extends CoffeeDecorator {

    private double chocolatePrice;

    public ChocolateDecorator(Coffee coffee) {
        super(coffee);
        this.chocolatePrice = 0.80;
    }

    @Override
    public double getCost() {
        return coffee.getCost() + chocolatePrice;
    }

    public double getChocolatePrice() {
        return chocolatePrice;
    }

    public void setChocolatePrice(double chocolatePrice) {
        this.chocolatePrice = chocolatePrice;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "\n + A delightful chocolatey twist to enhance your drink. (ChocolateDecorator)\n";
    }
}
