package data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Circle extends Form implements Mesurable2D{

    private Point center;
    private double radius;

    @Override
    public void translate(double deltaX, double deltaY) {
        this.center.translate(deltaX, deltaY);
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * this.radius;
    }

    @Override
    public double surface() {
        return Math.PI * Math.pow(radius, 2);
    }
}
