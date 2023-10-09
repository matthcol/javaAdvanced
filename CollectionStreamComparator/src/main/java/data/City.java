package data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class City implements Comparable<City> {
    private String name;
    private int population;
    private int distanceSea;

    /**
     * define natural order on type City
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(City o) {
        // TODO: define order here
        return 0;
    }
}
