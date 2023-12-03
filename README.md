# Hausaufgabe Nr. 4

In dieser Hausaufgabe geht es darum, das Pizza-Chatbot-Programm so umzustrukturieren, dass es u.a. leichter erweitert werden kann. 

## Abgabe

Nehmen Sie das Assignment auf Github Classroom an und wählen Sie Ihren Namen aus der Liste aus.
Dadurch wird ein (dieses) Repository für Sie erstellt. 
Klonen Sie das Repo und pushen Sie Ihre Lösungen in mehreren sinnvoll zusammengefassten Commits (z.B. einer pro Teilaufgabe, es können aber auch gerne mehr sein).
Sie müssen Ihre Lösung bis zum 8. Dezember um 23:59 Uhr gepusht haben, alle Tests müssen grün sein (auch auf GitHub), und Sie müssen spätestens in der darauf folgenden von Ihnen belegten Übungsgruppe Ihre Lösung kurz demonstrieren.

## Aufgabenstellung

0. Übertragen Sie die Lösungen Ihrer letzten Hausaufgabe in dieses Repository, führen Sie die Tests aus, und betrachten Sie dann die Klassen `IngredientBuilder`, `Menu` und die dazugehörigen Tests wie etwa `IngredientBuilderTest`, aber auch den leicht veränderten `UIParserBuilderIntegrationTest`.
1. Implementieren Sie im Paket `domain` eine Zutaten-Klassenhierarchie, indem Sie erst die Klasse `Ingredient` zu einem Interface machen. Dieses Interface wird von einer abstrakten Klasse `AbstractIngredient` implementiert. Von dieser abstrakten Klasse erben nun vier konkrete Klassen: `Base`, `Protein`, `Topping` und `Sauce`. Instanziieren Sie die jeweils passende Klasse in der Methode `build` des `IngredientBuilder`, so dass der erste Testfall von `IngredientBuilderTest` grün wird.
2. Überschreiben Sie (`override`) in den konkreten `Ingredient`-Unterklassen die `toString`-Methode so, dass der fehlschlagende `UIParserBuilderIntegrationTest` grün wird, und zwar ohne die Namen der Zutaten in der Klasse `Menu` zu ändern.
3. Überschreiben Sie in Ihrer `Ingredient`-Klassenhierarchie die Methoden `equals` und `hashCode` so, dass der `IngredientBuilderTest` grün wird. Implementieren Sie die Methoden dort in der Klassenhierarchie, wo am wenigsten Redundanz dadurch entsteht.  
4. Betrachten Sie die Methode `checkRules` im `PizzaBuilder` und fügen Sie zwei Testfälle zum `PizzaBuilderTest` hinzu, welche die zwei hinzugekommenen Regeln testen (mind. eine Sauce, keine zwei Teig-Basen). 
5. Der Umstand, dass die `checkRules`-Methode sich darauf verlassen muss, dass die Zutaten in der Klasse `Menu` in die richtigen Kategorien-Maps (`bases`, `sauces`, etc.) einsortiert sind, um zu funktionieren ist fehleranfällig und erzeugt eine unnötige Abhängigkeit zwischen dem `PizzaBuilder` und der `Menu`-Klasse. Dadurch lässt sich bspw. die `PizzaBuilder`-Klasse nicht mit Dummy-Zutaten testen, weshalb der `PizzaBuilderTest` fehlschlägt. Jetzt wo Sie unterschiedliche Implementierungsklassen eines gemeinsamen `Ingredient`-Interfaces haben, können die Objekte dieser Klassen selbst wissen, zu welcher Kategorie sie gehören. Deklarieren Sie daher eine Methode `getCategory()` (Rückgabetyp `Ingredient.Category`) im Interface `Ingredient`, implementieren Sie die Methode dann in allen konkreten Zutaten-Klassen (`Base`, `Sauce`, etc.) und passen Sie die Methode `checkRules` so an, dass sie die `getCategory`-Methode anstelle der Maps der `Menu`-Klasse benutzt.  

## Hinweise 
- Zu 1.: Beim Erstellen Ihrer neuen Klassenhierarchie können Sie gerne versuchen Gebrauch von automatischen Refactorings zu machen, bspw. `Extract Interface`.
- Zu 2.: Beachten Sie, dass die Methoden `getName` und `toString` teils unterschiedliche Werte zurückgeben müssen, damit alle Tests grün werden.
- Zu 3.: Für die etwas tolerantere Implementierung der `equals`- und `hashCode`-Methoden von Zutaten schauen Sie sich die Methode `trim` der Klasse `String` an, sowie die Methoden `compareTo` bzw. `doubleValue` der Klasse `BigDecimal`.
