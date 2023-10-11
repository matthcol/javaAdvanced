package validation.tu.fixture;

import lombok.*;
import validation.annotation.Min;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DataMinConstraintMisplaced {
    @Min(0.0)
    private String name;
}
