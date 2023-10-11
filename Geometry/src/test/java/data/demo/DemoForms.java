package data.demo;

import data.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.spec.RSAOtherPrimeInfo;
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
        var totalSurface = forms.stream()
                .filter(f -> f instanceof Mesurable2D)
                .map(f -> (Mesurable2D) f)
                .mapToDouble(Mesurable2D::surface)
                .sum();
        System.out.println(totalSurface);
    }

    @Test
    void demoSwitchCaseBefore(){
        for (var form: forms){
            if (form instanceof WeightedPoint){
                WeightedPoint wp = (WeightedPoint) form;
                System.out.println(wp.getWeight());
            } else if (form instanceof  Point) {
                Point pt = (Point) form;
                System.out.println(pt.getX() + ", " + pt.getY());
            } else if (form instanceof Mesurable2D){
                Mesurable2D m = (Mesurable2D) form;
                System.out.println(m.perimeter() + "," + m.surface());
            }
        }
    }

    @Test
    void demoModernIfInstanceOf(){
        for (var form: forms){
            // modern form of instance of + cast new variable
            if (form instanceof Point pt) {
                System.out.println("Point: " + pt.getX() + ", " + pt.getY());
            }
            // NB:
            if (form.getClass() == Point.class) {
                Point pt = (Point) form;
                System.out.println("Point (created as): " + pt.getX() + ", " + pt.getY());
            }
        }
    }

}