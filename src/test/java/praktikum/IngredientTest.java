package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {
    private Ingredient ingredient;

    @BeforeEach
    public void setUp() {
        ingredient = new Ingredient(IngredientType.FILLING, "Lettuce", 10.0f);
    }

    @Test
    public void testGetType() {
        assertEquals(IngredientType.FILLING, ingredient.getType());
    }

    @Test
    public void testGetName() {
        assertEquals("Lettuce", ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(10.0f, ingredient.getPrice(), 0);
    }
}
