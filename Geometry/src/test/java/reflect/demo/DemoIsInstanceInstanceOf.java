package reflect.demo;

import data.Circle;
import data.Form;
import data.Point;
import data.Triangle;
import org.junit.jupiter.api.Test;
import reflect.ClassTools;
import validation.tu.fixture.BadPojoNoGetter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DemoIsInstanceInstanceOf {

//    @Test
//    void demoCompilationError(){
//        Point pt = new Point();
//        // Compilation error on following line
//        if (pt instanceof BadPojoNoGetter) {
//
//        }
//    }

    @Test
    void demoNoDynamicError(){
        Stream<Form> formStream = Stream.of(
                new Point(),
                new Circle(),
                new Triangle()
        );
        var impossibleRes = ClassTools.filterByType(formStream, BadPojoNoGetter.class)
                .toList();
    }

    @Test
    void demoNoDynamicError2(){
        Point pt = new Point();
        // Warning IntelliJ
        boolean ok = BadPojoNoGetter.class.isInstance(pt);
        System.out.println(ok);
    }
}