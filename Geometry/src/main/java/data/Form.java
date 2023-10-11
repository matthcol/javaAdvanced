package data;

import lombok.*;
import lombok.experimental.SuperBuilder;
import validation.annotation.NotNull;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@ToString
public abstract class Form {
    @NotNull
    private String name;

    /**
     * translate this form
     * @param deltaX horizontal translate
     * @param deltaY vertical translate
     */
    public abstract void translate(double deltaX, double deltaY);
}
