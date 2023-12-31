package validation.tu;

import data.Circle;
import data.Point;
import data.WeightedPoint;
import validation.tu.fixture.BadPojoNoGetter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import validation.ValidationException;
import validation.Validations;
import validation.tu.fixture.DataMinConstraintMisplaced;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void testIsValidNotNullMinOk() {
        var ptA = Point.builder()
                .name("A")
                .x(4.5)
                .y(7.5)
                .build();
        var circleC = Circle.builder()
                .name("C")
                .center(ptA)
                .radius(3.0)
                .build();
        var ok = Validations.isValid(circleC);
        assertTrue(ok, "circle is valid");
    }

    @Test
    void testIsValidNotNullKo() {
        var circleC = Circle.builder()
                .name("C")
                .center(null)
                .radius(3.0)
                .build();
        var ok = Validations.isValid(circleC);
        assertFalse(ok, "circle is not valid");
    }

    @Test
    void testIsValidKoNoGetter() {
        var circleC = BadPojoNoGetter.builder()
                .name("bad pojo")
                .build();
        ValidationException ex = assertThrows(ValidationException.class, () -> Validations.isValid(circleC));
        assertEquals("NoSuchMethodException", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-3.5, 0.0, -0.0})
    void testIsValidMinKo(double badRadius) {
        var ptA = Point.builder()
                .name("A")
                .x(4.5)
                .y(7.5)
                .build();
        var circleC = Circle.builder()
                .name("C")
                .center(ptA)
                .radius(badRadius)
                .build();
        var ok = Validations.isValid(circleC);
        assertFalse(ok, "circle is not valid");
    }

    @Test
    void testIsValidMinKoWrongType(){
        DataMinConstraintMisplaced data = DataMinConstraintMisplaced.builder()
                .name("A")
                .build();
        assertThrows(ValidationException.class, () -> Validations.isValid(data));
    }

    @Test
    void testIsValidNotNullKoInParentClass(){
        var wp = WeightedPoint.builder()
                .name(null)
                .x(3.5)
                .y(4.5)
                .weight(12.0)
                .build();
        assertFalse(Validations.isValid(wp), "weighted point is not valid (no name)");
    }
}
