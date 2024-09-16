package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BurgerTest {
    private Bun bun;
    private Burger burger;

    @BeforeEach
    public void setUp() {
        bun = mock(Bun.class);
        when(bun.getName()).thenReturn("Sesame");
        when(bun.getPrice()).thenReturn(50.0f);

        burger = new Burger();
        burger.setBuns(bun);

    }

    @Test
    public void testAddIngredient() {
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("Lettuce");
        when(ingredient.getPrice()).thenReturn(10.0f);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));

    }

    @Test
    public void testRemoveIngredient() {
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("Lettuce");
        when(ingredient.getPrice()).thenReturn(10.0f);

        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @ParameterizedTest
    @MethodSource("provideIngredientPrices")
    public void testGetPrice(List<Ingredient> ingredients, float expectedPrice) {
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }

    private static Stream<Arguments> provideIngredientPrices() {
        return Stream.of(
                Arguments.of(new ArrayList<Ingredient>(), 100.0f),
                Arguments.of(List.of(createMockIngredient( "Lettuce", 10.0f)),
                        110.0f),
                Arguments.of(List.of(
                                createMockIngredient( "Lettuce", 10.0f),
                                createMockIngredient("Tomato", 15.0f)),
                        125.0f)
        );
    }

    private static Ingredient createMockIngredient(String name, float price) {
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn(name);
        when(ingredient.getPrice()).thenReturn(price);
        return ingredient;
    }

    @Test
    public void testGetReceipt() {
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("Lettuce");
        when(ingredient.getPrice()).thenReturn(10.0f);

        burger.addIngredient(ingredient);

        String expected = String.format("(==== %s ====)%n", bun.getName()) +
                String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(), ingredient.getName()) +
                String.format("(==== %s ====)%n", bun.getName()) +
                String.format("%nPrice: %.6f%n", burger.getPrice()); // Форматируем с 6 знаками после запятой

        assertEquals(expected, burger.getReceipt());

    }
}
