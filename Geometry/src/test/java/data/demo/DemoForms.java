package data.demo;

import data.Circle;
import data.Form;
import data.Point;
import data.WeightedPoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DemoForms {

    static List<Form> forms;

    @BeforeAll
    static void initDemoForms(){
        var ptA = Point.builder()
                .name("A")
                .x(4.5)
                .y(7.5)
                .build();
        var ptB = WeightedPoint.builder()
                .name("B")
                .x(2.25)
                .y(3.75)
                .weight(10.0)
                .build();
        forms = List.of(
                new Point(),
                new WeightedPoint(),
                ptA,
                ptB,
                Circle.builder()
                        .name("C")
                        .center(ptA)
                        .radius(3.0)
                        .build()
        );
        System.out.println(forms);
    }

    @Test
    void computeMaximumPointX() {
        var optXMax = forms.stream()
                .filter(f -> f instanceof Point)
                .map(f -> (Point) f)
                .mapToDouble(Point::getX)
                .max();
        System.out.println(optXMax);
    }

    @Test
    void computeTotalSurface(){

    }

}