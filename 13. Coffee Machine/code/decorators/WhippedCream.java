package decorators;

import Interfaces.Coffee;
import abstractClasses.CoffeeDecorator;

public class WhippedCream extends CoffeeDecorator { // addOn class
    public WhippedCream(Coffee coffee) {
        super(coffee);
    }

    public double getCost() {
        return super.getCost() + 15;
    }

    public String getDescription() {
        return super.getDescription() + ", Whipped Cream";
    }
}
