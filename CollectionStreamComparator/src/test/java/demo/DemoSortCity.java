package demo;

import data.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.Collator;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
                        .distanceSea(150)
                        .build(),
                City.builder()
                        .name("Bordes")
                        .population(3000)
                        .distanceSea(120)
                        .build(),
                City.builder()
                        .name("Bordes")
                        .population(4000)
                        .distanceSea(160)
                        .build()
        );
    }

    static Stream<Arguments> cityComparatorSource() {
        var collatorFrenchFrance = Collator.getInstance(
                Locale.of("fr", "FR")
        );
        return Stream.of(
                // natural order on City
                Arguments.of("city natural order", Comparator.<City>naturalOrder()),
                // by name (natural order on String)
                Arguments.of("by name", Comparator.comparing(City::getName)),
                // by distanceSea, population
                Arguments.of("by distance sea, population",
                        Comparator.comparing(City::getDistanceSea)
                                .thenComparing(City::getPopulation)
                ),
                // by name (case insensitive)
                Arguments.of("by name",
                        Comparator.comparing(City::getName, String::compareToIgnoreCase)),
                // by name (locale fr, case insensitive)
                Arguments.of("by name (locale fr, CI)",
                        Comparator.comparing(City::getName, collatorFrenchFrance)
                ),
                // by name (locale fr, case insensitive), population
                Arguments.of("by name (locale fr, CI), population",
                        Comparator.comparing(City::getName, collatorFrenchFrance)
                                .thenComparing(City::getPopulation)
                )
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

    @Test
    void demoHashSet(){
        var citySet =  new HashSet<>(cities);
        var city1 = cities.get(0);
        assertTrue(citySet.contains(city1));
        var city2 = City.builder()
                .name("Pau")
                .population(77000)
                .distanceSea(100)
                .build(); // same content as city1
        assertTrue(citySet.contains(city2));
    }
}
