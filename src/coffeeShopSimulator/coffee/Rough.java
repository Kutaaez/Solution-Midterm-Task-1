package coffeeShopSimulator.coffee;

import java.util.ArrayList;
import java.util.List;

public class Rough extends Coffee {
    private double coffeeCost = 2.50;
    private String coffeeDescription = "Rough \nA bold and unfiltered coffee with a strong, raw taste.";

    public double getCost() {
        return coffeeCost;
    }


    public String getDescription() {
        return coffeeDescription;
    }
}
