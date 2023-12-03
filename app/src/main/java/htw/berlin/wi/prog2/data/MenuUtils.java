package htw.berlin.wi.prog2.data;

import htw.berlin.wi.prog2.domain.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuUtils {

    public static List<String> focusOnNames(Map<Long, Ingredient> articles) {
        List<String> names = new ArrayList<>();
        for (Ingredient art : articles.values()) names.add(art.getName());
        return names;
    }

    /**
     *
     * @param articles
     * @return Map mit Namen als Schlüssel und ID als Wert
     */

    public static Map<String, Long> focusOnNameAndInvert(Map<Long, Ingredient> articles) {
        Map<String, Long> invertedMap = new HashMap<>();

        if(articles.isEmpty()) {
            throw new IllegalArgumentException ("Kein Artikel vorhanden");
        } else {
            articles.forEach((id, ingredient) -> invertedMap.put(ingredient.getName(), id)); // forEach: eine methode für jedes schlüssel wert paar

            return invertedMap; // TODO hier implementieren und korrekte Ergebnis-Map zurückgeben
        }

    }

    /**
     *
     * @param idsAndCount
     * @param articles
     * @return Liste mit Zutaten
     */

    public static List<Ingredient> ingredientsFromIdAndCount(Map<Long, Integer> idsAndCount, Map<Long, Ingredient> articles) {
        List<Ingredient> ingredients = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : idsAndCount.entrySet()) {
            Long id = entry.getKey();
            if (articles.containsKey(id)) {
                for (int i = 0; i < entry.getValue(); i++) {
                    ingredients.add(articles.get(id));
                }
            }
        }
        return ingredients;
    }
}
