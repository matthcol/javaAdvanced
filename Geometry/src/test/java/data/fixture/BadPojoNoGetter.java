package data.fixture;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.annotation.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class BadPojoNoGetter {

    @NotNull
    private String name;
}
