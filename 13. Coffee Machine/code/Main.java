import Interfaces.Coffee;
import coffeeImpl.Espresso;
import coffeeImpl.Latte;
import decorators.Milk;
import decorators.Sugar;
import decorators.WhippedCream;

public class Main {
    public static void main(String[] args) {
        // Standard Coffee (no customization)
        Coffee espresso = new Espresso();
        System.out.println(espresso.getDescription() + " = Rs." + espresso.getCost());

        // Customized Coffee using Decorators
        Coffee customCoffee = new Latte();
        customCoffee = new Milk(customCoffee);
        customCoffee = new Sugar(customCoffee);
        customCoffee = new WhippedCream(customCoffee);

        System.out.println(customCoffee.getDescription() + " = Rs." + customCoffee.getCost());
    }
}
