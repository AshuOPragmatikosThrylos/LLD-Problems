package coffeeImpl;

import Interfaces.Coffee;

public class Latte implements Coffee { // baseClass
    public double getCost() {
        return 90;
    }

    public String getDescription() {
        return "Latte";
    }
}
