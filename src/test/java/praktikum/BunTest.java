package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @ParameterizedTest
    @CsvSource({
            "Student Buns, 0.99",
            "Platinum Buns, 10000000000.0",
            "Special Buns, 0.0",
            "Standard Buns, 100.0"
    })
    public void testBunCreation(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice(), 0.001);
    }
}
