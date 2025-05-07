package coffeeImpl;

import Interfaces.Coffee;

public class Espresso implements Coffee { // baseClass
    public double getCost() {
        return 70;
    }

    public String getDescription() {
        return "Espresso";
    }
}
