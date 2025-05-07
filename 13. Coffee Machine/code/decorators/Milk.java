package decorators;

import Interfaces.Coffee;
import abstractClasses.CoffeeDecorator;

public class Milk extends CoffeeDecorator { // addOn class
    public Milk(Coffee coffee) {
        super(coffee);
    }

    public double getCost() {
        return super.getCost() + 10;
    }

    public String getDescription() {
        return super.getDescription() + ", Milk";
    }
}
