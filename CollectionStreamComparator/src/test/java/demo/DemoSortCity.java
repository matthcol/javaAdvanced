package demo;

import data.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class DemoSortCity {

    List<City> cities;

    @BeforeEach
    void initCities() {
        cities = new ArrayList<>();
        Collections.addAll(cities,
                City.builder()
                        .name("Pau")
                        .population(77000)
                        .distanceSea(100)
                        .build(),
                City.builder()
                        .name("Toulouse")
                        .population(470000)
                        .distanceSea(150)
                        .build(),
                City.builder()
                        .name("lyon")
                        .population(470000)
                        .distanceSea(310)
                        .build(),
                City.builder()
                        .name("aÿ-champagne")
                        .population(5250)
                        .distanceSea(400)
                        .build(),
                City.builder()
                        .name("Azas")
                        .population(664)
                        .distanceSea(145)
                        .build()
        );
    }

    static Stream<Arguments> cityComparatorSource() {
        return Stream.of(
                Arguments.of("city natural order", Comparator.<City>naturalOrder()),
                Arguments.of("by name", Comparator.comparing(City::getName)),
                // by distanceSea, population
                // by name (case insensitive)
                // by name (locale fr, case insensitive)
                // by name (locale fr, case insensitive), population

        );
    }

    @ParameterizedTest
    @MethodSource("cityComparatorSource")
    void demoNavigableSet(String comparatorName, Comparator<City> comparator){
        System.out.println("Sorted set cities with comparator: " + comparatorName);
        var citiesSorted = new TreeSet<>(comparator);
        citiesSorted.addAll(cities);
        System.out.println(citiesSorted);
    }

    @Test
    void demoSortList(){
        // cities.sort(Comparator.naturalOrder());
        Collections.sort(cities);
        System.out.println(cities);
    }
}
