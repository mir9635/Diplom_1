package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {
    private Ingredient ingredientFilling;
    private Ingredient ingredientSauce;

    @BeforeEach
    public void setUp() {
        ingredientFilling = new Ingredient(IngredientType.FILLING, "Lettuce", 10.0f);
        ingredientSauce = new Ingredient(IngredientType.SAUCE, "Traditional galactic sauce", 15.0f);

    }

    @Test
    public void testGetTypeFilling() {
        assertEquals(IngredientType.FILLING, ingredientFilling.getType());
    }

    @Test
    public void testGetNameFilling() {
        assertEquals("Lettuce", ingredientFilling.getName());
    }

    @Test
    public void testGetPriceFilling() {
        assertEquals(10.0f, ingredientFilling.getPrice(), 0);
    }

    @Test
    public void testGetTypeSauce() {
        assertEquals(IngredientType.SAUCE, ingredientSauce.getType());
    }

    @Test
    public void testGetNameSauce() {
        assertEquals("Traditional galactic sauce", ingredientSauce.getName());
    }

    @Test
    public void testGetPriceSauce() {
        assertEquals(15.0f, ingredientSauce.getPrice(), 0);
    }
}
