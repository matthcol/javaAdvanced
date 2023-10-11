package validation.demo;

import data.Circle;
import data.Point;
import org.junit.jupiter.api.Test;
import validation.Validations;

import static org.junit.jupiter.api.Assertions.*;

class ValidationsDemo {

    @Test
    void demoIntrospectCircle(){
        var ptA = Point.builder()
                .name("A")
                .x(4.5)
                .y(7.5)
                .build();
        var circleC =  Circle.builder()
                .name("C")
                .center(ptA)
                .radius(3.0)
                .build();
        Validations.introspectValidation(circleC);
    }

    @Test
    void testIsValidNotNull(){
        var ptA = Point.builder()
                .name("A")
                .x(4.5)
                .y(7.5)
                .build();
        var circleC =  Circle.builder()
                .name("C")
                .center(ptA)
                .radius(3.0)
                .build();
        var ok = Validations.isValid(circleC);
        assertTrue(ok, "circle is valid");
    }
}