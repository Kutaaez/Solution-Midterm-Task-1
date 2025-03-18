package coffeeShopSimulator.coffee;

import java.util.ArrayList;
import java.util.List;

public class Cappuccino extends Coffee {
    private double coffeeCost = 3.00;
    private String coffeeDescription =  "Cappucino \nA perfect balance of espresso, steamed milk, and frothy milk foam.";

    public double getCost() {
        return coffeeCost;
    }


    public String getDescription() {
        return coffeeDescription;
    }
}
