package coffeeShopSimulator.coffee;

import java.util.ArrayList;
import java.util.List;

public class Latte extends Coffee {
    private double coffeeCost = 4.50;
    private String coffeeDescription = "Latte \nSmooth and creamy coffee with a generous amount of steamed milk.";

    public double getCost() {
        return coffeeCost;
    }


    public String getDescription() {
        return coffeeDescription;
    }
}
