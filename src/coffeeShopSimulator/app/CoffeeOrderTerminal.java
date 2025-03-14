package coffeeShopSimulator.app;

import coffeeShopSimulator.coffee.Coffee;

import coffeeShopSimulator.factory.CoffeeFactory;
import coffeeShopSimulator.factory.DecoratorFactory;


import java.util.IllegalFormatException;
import java.util.Scanner;

public class CoffeeOrderTerminal {
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Coffee Shop! ☕");
        System.out.print("Enter coffee type (Espresso, Latte, etc.): ");
        System.out.println("Prices:" +
                "☕ Coffee Prices & Descriptions\n" +
                "Espresso – $3.00\n" +
                "\"A strong and rich coffee shot with an intense flavor.\"\n" +
                "\n" +
                "Cappuccino – $4.00\n" +
                "\"A perfect balance of espresso, steamed milk, and frothy milk foam.\"\n" +
                "\n" +
                "Latte – $4.50\n" +
                "\"Smooth and creamy coffee with a generous amount of steamed milk.\"\n" +
                "\n" +
                "Rough Coffee – $2.50\n" +
                "\"A bold and unfiltered coffee with a strong, raw taste.\"\n" +
                "\n" +
                "\uD83E\uDD5B Add-ons Prices & Descriptions\n" +
                "Milk – +$0.50\n" +
                "\"Adds a smooth and creamy texture to your coffee.\"\n" +
                "\n" +
                "Caramel Syrup – +$0.70\n" +
                "\"A sweet caramel touch for a richer flavor.\"\n" +
                "\n" +
                "Chocolate Syrup – +$0.80\n" +
                "\"A delightful chocolatey twist to enhance your drink.\"\n" +
                "\n" +
                "Whipped Cream – +$0.60\n" +
                "\"A light and fluffy topping for a sweeter, creamier taste.\"");
        String typeOfCoffee = scanner.nextLine().trim().toLowerCase();
        Coffee coffee;

        //create coffee use of pattern factory
        try{
            coffee = CoffeeFactory.createCoffee(typeOfCoffee);
            System.out.printf("Your %s has been made!",typeOfCoffee);

            }
        catch(IllegalArgumentException e ){
            System.out.println("Invalid coffee type. Exiting...");
            return;
        }
        //create while cycle for adding extra ingredints for our coffee, this we use decorator pattern for announce or adding new information for our classes (coffee).

        while(true){
            System.out.print("Add extra ingredient for your coffee!\nOr write done if you do not need ingredients");
            String extraIndredientName = scanner.nextLine().trim().toLowerCase();
            if(extraIndredientName.equals("done")){

                break;
            }
            try{
               coffee = DecoratorFactory.addIngredint(coffee, extraIndredientName);
                System.out.printf(" %s ingredeint was added!",extraIndredientName);

            }
            catch(IllegalArgumentException e ){
                System.out.println("Invalid ingredient type. Exiting...");
                return;
            }

        }
        System.out.println("\nYour order: " + coffee.getDescription());
        System.out.println("Total cost: $" + coffee.getCost());
        System.out.println("Enjoy your coffee! ☕");
        scanner.close();

    }
}
