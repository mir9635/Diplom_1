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

public class BurgerTest {
    private Bun bun;
    private Burger burger;

    @BeforeEach
    public void setUp() {
        bun = new Bun("Sesame", 50.0f);
        burger = new Burger();
        burger.setBuns(bun);
    }

    @Test
    public void testAddIngredient() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Lettuce", 10.0f);
        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Lettuce", 10.0f);
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
                Arguments.of(List.of(new Ingredient(IngredientType.FILLING, "Lettuce", 10.0f)),
                        110.0f),
                Arguments.of(List.of(
                                new Ingredient(IngredientType.FILLING, "Lettuce", 10.0f),
                                new Ingredient(IngredientType.FILLING, "Tomato", 15.0f)),
                        125.0f)
        );
    }

    @Test
    public void testGetReceipt() {

        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Lettuce", 10.0f);
        burger.addIngredient(ingredient);

        String expectedHeaderFooter = "(==== Sesame ====)";
        String expectedIngredient = "= filling Lettuce =";
        String expectedPrice = "Price: 110,000000";

        String actualReceipt = burger.getReceipt();

        // Разделяем строки для проверки порядка
        String[] actualLines = actualReceipt.split("\n");
        String[] expectedLines = {
                expectedHeaderFooter,
                expectedIngredient,
                expectedHeaderFooter,
                "",
                expectedPrice
        };

        // Проверяем, что все строки совпадают по порядку
       assertEquals(expectedLines.length, actualLines.length, "Количество строк не совпадает.");

        for (int i = 0; i < expectedLines.length; i++) {
            String actualLine = actualLines[i].trim();
            String expectedLine = expectedLines[i].trim();

            // Сравниваем строки
             assertEquals(expectedLine, actualLine, "Строка не совпадает на позиции " + i);
        }
    }
}
