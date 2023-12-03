package htw.berlin.wi.prog2.domain;

import htw.berlin.wi.prog2.data.Menu; // TODO Diesen Import sollten Sie nach Teilaufgabe 4 entfernen können

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PizzaBuilder {

    private List<Ingredient> ingredients = new ArrayList<>();

    public enum CreationStyle {
        PRECOMPUTED,
        DYNAMICALLY_COMPUTED
    }

    private CreationStyle creationStyle;

    public void setCreationStyle(CreationStyle creationStyle) {
        this.creationStyle = creationStyle;
    }

    public PizzaBuilder add(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public Pizza build() {
        checkRules();
        return creationStyle == CreationStyle.PRECOMPUTED ? buildPrecomputed() : buildDynamicallyComputed();
    }

    private void checkRules() {
        if(ingredients.size() < 2) throw new IllegalPizzaException("Nicht genügend Zutaten");

        // TODO hier mit ingredient.getCategory() statt mit Menu.bases und Menu.sauces arbeiten
        if(ingredients.stream().filter((ing) -> Menu.bases.containsValue(ing)).collect(Collectors.toList()).size() > 1)
            throw new IllegalPizzaException("Zwei mal Teig-Basis in einer Pizza geht nicht");

        if(ingredients.stream().filter((ing) -> Menu.sauces.containsValue(ing)).collect(Collectors.toList()).size() < 1)
            throw new IllegalPizzaException("Eine Pizza braucht mindestens eine Sauce");
    }

    private Pizza buildPrecomputed() {
        BigDecimal price = BigDecimal.ZERO;
        int calories = 0;
        List<String> ingredientNames = new ArrayList<>();

        for (Ingredient ing : ingredients) {
            price = price.add(ing.getPrice());
            calories += ing.getCalories();
            ingredientNames.add(ing.getName());
        }
        ingredients.clear();

        return new PrecomputedPizza(price, calories, ingredientNames);
    }

    private Pizza buildDynamicallyComputed() {
        List<Ingredient> ingsToPass = List.copyOf(ingredients);
        ingredients.clear();
        return new DynamicallyComputedPizza(ingsToPass);
    }

    public PizzaBuilder(CreationStyle creationStyle) {
        this.creationStyle = creationStyle;
    }

    public PizzaBuilder() {
        this(CreationStyle.PRECOMPUTED);
    }
}
