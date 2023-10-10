package data;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Point extends Form {

    private double x;
    private double y;

    public Point(String name, double x, double y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public double distance(Point other){
        return Math.hypot(this.x - other.x, this.y - other.y);
    }
}
