package data;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class City implements Comparable<City> {
    private String name;
    private int population;
    private int distanceSea;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        City city = (City) o;
//        return population == city.population && distanceSea == city.distanceSea && Objects.equals(name, city.name);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, population, distanceSea);
//    }

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
