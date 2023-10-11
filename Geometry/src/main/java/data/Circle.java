package data;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import validation.annotation.Min;
import validation.annotation.NotNull;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Circle extends Form implements Mesurable2D{

    @JsonAlias
    @NotNull
    private Point center; // not null

    // @Min(0)
    @Min(value = 0, inclusive = false)
    private double radius; // radius > 0

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
