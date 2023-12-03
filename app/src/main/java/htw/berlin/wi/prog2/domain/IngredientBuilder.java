package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

public class IngredientBuilder {

    private String name;
    private BigDecimal price;
    private int calories;

    public IngredientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public IngredientBuilder setPrice(String price) {
        this.price = new BigDecimal(price);
        return this;
    }

    public IngredientBuilder setCals(int calories) {
        this.calories = calories;
        return this;
    }

    public Ingredient build(Ingredient.Category cat) {
        return new Ingredient(name, price, calories); // TODO hier die richtige Unterklasse instanziieren
    }
}
