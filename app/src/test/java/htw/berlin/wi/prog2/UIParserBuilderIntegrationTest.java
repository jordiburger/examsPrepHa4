package htw.berlin.wi.prog2;

import htw.berlin.wi.prog2.parsing.CountingInputParser;
import htw.berlin.wi.prog2.parsing.ExtendableInputParser;
import htw.berlin.wi.prog2.domain.PizzaBuilder;
import htw.berlin.wi.prog2.ui.CommandLineUI;
import htw.berlin.wi.prog2.ui.UserInputWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UIParserBuilderIntegrationTest {

    @Test
    @DisplayName("should state the correct ingredients and price")
    void integrationWithCountingParser() {
        String initialQuestion = "Was möchtest du gerne bestellen?";
        UserInputWrapper input = mock(UserInputWrapper.class);
        when(input.ask(contains(initialQuestion)))
                .thenReturn("Ich hätte gerne eine Italian-style Pizza mit Champignons, Basilikum, Seitan-Chicken und Olivenöl");
        when(input.ask(not(contains(initialQuestion)))).thenReturn("Auf Wiedersehen");

        PizzaBuilder builder = new PizzaBuilder(PizzaBuilder.CreationStyle.PRECOMPUTED);

        ExtendableInputParser parser = new CountingInputParser();

        CommandLineUI ui = new CommandLineUI(input, builder, parser);

        assertEquals("Auf Wiedersehen", ui.launch());

        String expectedQuestion =
                "In Ordnung. Deine Pizza mit [Basilikum als Topping, Champignons als Topping, Italian-style-Teig, Olivenöl als Sauce, Seitan-Chicken] kostet 2.80 Euro. Willst du die Bestellung abschliessen?";

        verify(input).ask(expectedQuestion);
    }
}
