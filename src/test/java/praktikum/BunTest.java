package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {
    private Bun bun;

    @BeforeEach
    public void setUp() {
        bun = new Bun("Sesame", 50.0f);
    }

    @Test
    public void testGetName() {
        assertEquals("Sesame", bun.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(50.0f, bun.getPrice(), 0);
    }
}
