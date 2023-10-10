package data.demo;

import data.Point;
import data.WeightedPoint;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DemoForms {

    @Test
    void demoForms(){
        List<Point> forms = List.of(
                new Point(),
                new WeightedPoint(),
                Point.builder()
                        .name("A")
                        .x(4.5)
                        .y(7.5)
                        .build(),
                WeightedPoint.builder()
                        .name("B")
                        .x(2.25)
                        .y(3.75)
                        .weight(10.0)
                        .build()
        );
        System.out.println(forms);
    }

}