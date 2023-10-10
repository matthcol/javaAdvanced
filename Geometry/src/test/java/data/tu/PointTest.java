package data.tu;

import data.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testAllArgsConstructor(){
        var p = new Point("A", 3.5, 4.25);
        assertAll(
                () -> assertEquals("A", p.getName(), "name"),
                () -> assertEquals(3.5, p.getX(), "x"),
                () -> assertEquals(4.25, p.getY(), "y")
        );
    }

    @Test
    void testBuilderAllArgs(){
        var p = Point.builder()
                .name("A")
                .x(3.5)
                .y(4.25)
                .build();
        assertAll(
                () -> assertEquals("A", p.getName(), "name"),
                () -> assertEquals(3.5, p.getX(), "x"),
                () -> assertEquals(4.25, p.getY(), "y")
        );
    }

    @Test
    void doubleDummyTest(){
        assertNotEquals(0.3, 3*0.1);
        assertEquals(0.75, 3*0.25);
    }



}