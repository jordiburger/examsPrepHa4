package htw.berlin.wi.prog2.data;

import htw.berlin.wi.prog2.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuUtils {

    public static List<String> focusOnNames(Map<Long, Ingredient> articles) {
        List<String> names = new ArrayList<>();
        for (Ingredient art : articles.values()) names.add(art.getName());
        return names;
    }

    public static Map<String, Long> focusOnNameAndInvert(Map<Long, Ingredient> articles) {
        return Map.of(); // TODO hier implementieren und korrekte Ergebnis-Map zurückgeben
    }

    public static List<Ingredient> ingredientsFromIdAndCount(Map<Long, Integer> idsAndCount, Map<Long, Ingredient> articles) {
        return List.of(); // TODO hier implementieren und korrekte Ergebnis-Liste zurückgeben
    }
}
