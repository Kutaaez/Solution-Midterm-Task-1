package coffeeShopSimulator.factory;

import coffeeShopSimulator.coffee.*;

import java.util.Locale;
//creating class coffeefactory for generate coffee choose in info other classes.  It's class  one of develop pattern.

public class CoffeeFactory {
    public static Coffee createCoffee(String typeOfCoffee){
        switch(typeOfCoffee.toLowerCase(Locale.ROOT)){
            case "cappuccino","капучино":
                return new Cappuccino();

            case "latte","латте":
                return new Latte();

            case "rough","раф":
                return new Rough();

            case "espresso","эспрессо":
                return new Espresso();

            default:
                throw new IllegalArgumentException("Unknown coffee type: " + typeOfCoffee + "\nPlease rewrite type of coffee.");

        }

    }
}
