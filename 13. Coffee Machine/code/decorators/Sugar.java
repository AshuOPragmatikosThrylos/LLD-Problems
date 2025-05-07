package decorators;

import Interfaces.Coffee;
import abstractClasses.CoffeeDecorator;

public class Sugar extends CoffeeDecorator { // addOn class
    public Sugar(Coffee coffee) {
        super(coffee);
    }

    public double getCost() {
        return super.getCost() + 5;
    }

    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }
}
