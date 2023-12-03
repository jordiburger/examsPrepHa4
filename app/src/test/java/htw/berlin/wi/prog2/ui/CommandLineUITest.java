package htw.berlin.wi.prog2.ui;

import htw.berlin.wi.prog2.domain.DynamicallyComputedPizza;
import htw.berlin.wi.prog2.domain.PizzaBuilder;
import htw.berlin.wi.prog2.parsing.ExtendableInputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

// testing approach inspired from https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input#answer-6416591
public class CommandLineUITest {

    @Test
    @DisplayName("text input loop should get a first input line")
    void canGetInput() {
        String initialQuestion = "Was möchtest du gerne bestellen?";
        UserInputWrapper input = mock(UserInputWrapper.class);
        when(input.ask(contains(initialQuestion))).thenReturn("Ich bin mir nicht sicher");
        when(input.ask(not(contains(initialQuestion)))).thenReturn("Auf Wiedersehen");

        PizzaBuilder builder = mock(PizzaBuilder.class);
        when(builder.add(any())).thenReturn(builder);
        when(builder.build()).thenReturn(new DynamicallyComputedPizza(new ArrayList<>()));

        ExtendableInputParser parser = mock(ExtendableInputParser.class);
        when(parser.idsAndCountFromInput(anyString(), anyMap())).thenReturn(Map.of(1L, 1, 3L, 1));

        CommandLineUI ui = new CommandLineUI(input, builder, parser);

        assertEquals("Auf Wiedersehen", ui.launch());
    }

    @Test
    @DisplayName("calls the parser with correct input line")
    void canCallParser() {
        String initialQuestion = "Was möchtest du gerne bestellen?";
        String inputLine = "Ich hätte gerne eine Italian-style Pizza mit Schinken";
        UserInputWrapper input = mock(UserInputWrapper.class);
        when(input.ask(contains(initialQuestion))).thenReturn(inputLine);
        when(input.ask(not(contains(initialQuestion)))).thenReturn("Auf Wiedersehen");

        PizzaBuilder builder = mock(PizzaBuilder.class);
        when(builder.add(any())).thenReturn(builder);
        when(builder.build()).thenReturn(new DynamicallyComputedPizza(new ArrayList<>()));

        ExtendableInputParser parser = mock(ExtendableInputParser.class);
        when(parser.idsAndCountFromInput(anyString(), anyMap())).thenReturn(Map.of(1L, 1, 3L, 1));

        CommandLineUI ui = new CommandLineUI(input, builder, parser);

        assertEquals("Auf Wiedersehen", ui.launch());
        verify(parser).idsAndCountFromInput(eq(inputLine), anyMap());
    }
}
