package coffeeShopSimulator.coffee;

import java.util.ArrayList;
import java.util.List;

public class Espresso extends Coffee {
    private double coffeeCost = 3.00;
    private String coffeeDescription = "Espresso \nA strong and rich coffee shot with an intense flavor.";

    public double getCost() {
        return coffeeCost;
    }


    public String getDescription() {
        return coffeeDescription;
    }

}
