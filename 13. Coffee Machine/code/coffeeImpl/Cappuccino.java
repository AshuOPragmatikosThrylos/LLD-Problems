package coffeeImpl;

import Interfaces.Coffee;

public class Cappuccino implements Coffee { // baseClass
    public double getCost() {
        return 100;
    }

    public String getDescription() {
        return "Cappuccino";
    }
}
