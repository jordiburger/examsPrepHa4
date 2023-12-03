package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

public class Ingredient {
    private final String name;
    private final BigDecimal price;
    private final int calories;

    public Ingredient(String name, BigDecimal price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() { return this.getName(); }

    public enum Category {
        BASE,
        PROTEIN,
        TOPPING,
        SAUCE
    }
}
