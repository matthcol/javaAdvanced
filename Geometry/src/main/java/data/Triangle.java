package data;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Triangle extends Form implements Mesurable2D{

    private Point summit1;
    private Point summit2;
    private Point summit3;

    public double length1(){
        return summit1.distance(summit2);
    }

    public double length2(){
        return summit2.distance(summit3);
    }

    public double length3(){
        return summit3.distance(summit1);
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        Stream.of(summit1,summit2,summit3)
                .forEach(s -> s.translate(deltaX, deltaY));
    }

    @Override
    public double perimeter() {
        return length1() + length2() + length3();
    }

    @Override
    public double surface() {
        double d = perimeter() / 2;
        return Math.sqrt(d * (d - length1()) * (d - length2()) * (d -length3()));
    }
}
