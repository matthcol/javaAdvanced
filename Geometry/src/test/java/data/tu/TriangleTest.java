package data.tu;

import data.Point;
import data.Triangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    static Triangle triangle345;

    @BeforeAll
    static void initTriangle345(){
        var p1 = Point.builder()
                .x(1.5)
                .y(2.5)
                .build();
        var p2 = Point.builder()
                .x(4.5)
                .y(2.5)
                .build();
        var p3 = Point.builder()
                .x(1.5)
                .y(-1.5)
                .build();
        triangle345 = Triangle.builder()
                .summit1(p1)
                .summit2(p2)
                .summit3(p3)
                .build();
    }

    @Test
    void testPerimeter(){
        assertEquals(12, triangle345.perimeter());
    }

    @Test
    void testSurface(){
        assertEquals(6, triangle345.surface());
    }

}