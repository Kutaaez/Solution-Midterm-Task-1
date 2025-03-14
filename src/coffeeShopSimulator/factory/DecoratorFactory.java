package coffeeShopSimulator.factory;

import coffeeShopSimulator.coffee.*;
import coffeeShopSimulator.decorators.CaramelDecorator;
import coffeeShopSimulator.decorators.ChocolateDecorator;
import coffeeShopSimulator.decorators.MilkDecorator;
import coffeeShopSimulator.decorators.WhippedCreamDecorator;

import java.util.Locale;

public class DecoratorFactory {
    public static Coffee addIngredint(Coffee coffee, String typeOfIngredient){
        switch(typeOfIngredient.toLowerCase(Locale.ROOT)){
            case "milk","молоко":
                return new MilkDecorator(coffee);

            case "caramel","карамель":
                return new CaramelDecorator(coffee);

            case "whippedcream","крем":
                return new WhippedCreamDecorator(coffee);

            case "chocolate","шоколад":
                return new ChocolateDecorator(coffee);

            default:
                throw new IllegalArgumentException("Invalid ingredient: " + typeOfIngredient);

        }

    }
}
