package demo;

import data.City;
import lombok.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.Collator;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
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

    @Test
    void demoStreamJoining(){
        var cityTrip = cities.parallelStream()
                .map(City::getName)
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));
        System.out.println(cityTrip);
    }

    @Test
    void demoStreamTotalPopulation(){
        var totalPopulation = cities.parallelStream()
                .mapToInt(City::getPopulation)
                .sum();
        System.out.println("Total population: " + totalPopulation);
    }

    @ParameterizedTest
    @ValueSource(ints={0, 100, 200})
    void demoStreamMaxPopulationDistanceSeaLessThanX(int thresholdDistance){
        var optMaxPop = cities.parallelStream()
                .filter(city -> city.getDistanceSea() < thresholdDistance)
                .mapToInt(City::getPopulation)
                .max();
        System.out.println("Max population: " + optMaxPop);
    }

    @ParameterizedTest
    @ValueSource(ints={0, 100, 200})
    void demoStreamStatsPopulationDistanceSeaLessThanX(int thresholdDistance){
        var stats = cities.stream()
                .peek(city -> System.out.println("Before filter: " + city))
                .filter(city -> city.getDistanceSea() < thresholdDistance)
                .peek(city -> System.out.println("After filter: " + city))
                .mapToInt(City::getPopulation)
                .summaryStatistics();
        System.out.println("Stats population: " + stats);
    }

    @Test
    void demoStreamGenerator() {
        var res = LongStream.range(10, 1_000_000_000)
                .skip(500_000)
                .limit(10)
                .peek(System.out::println)
                .average();
        System.out.println(res);
    }

    @Test
    void demoStreamMapToObjToList(){
        long n = 1_000_000;
        var cityList = IntStream.iterate(1, x -> x + 2)
                .limit(n)
                .mapToObj(x -> City.builder()
                        .name("SuperCity_" + x)
                        .population(1000*x)
                        .distanceSea(x)
                        .build()
                )
                .toList(); // Java 17 shortcut
        // see 10 first results
        cityList.stream()
                .limit(10)
                .forEach(System.out::println);

    }

    // repeat this with
    // -  arrayList
    // - linkedList
    // - treeset with natural order
    // - treeset with order: pop desc, name asc

    static Stream<Supplier<? extends Collection<City>>> collectionCitySupplierSource() {
        var comparatorCity = Comparator.comparing(City::getPopulation, Comparator.reverseOrder())
                .thenComparing(City::getName);
        return Stream.of(
                ArrayList<City>::new, // default constructor
                // ArrayList<Object>::new,
                LinkedList::new, // default constructor
                TreeSet::new, // default constructor
                () -> new TreeSet<>(comparatorCity)
        );
    }
    @ParameterizedTest
    @MethodSource("collectionCitySupplierSource")
    void demoStreamMapToObjToCollection(Supplier<? extends Collection<City>> collectionCitySupplier){
        long n = 10_000;// 1_000_000;
        var cityCollection = IntStream.iterate(1, x -> x + 2)
                .limit(n)
                .mapToObj(x -> City.builder()
                        .name("SuperCity_" + x)
                        .population(1000*x)
                        .distanceSea(x)
                        .build()
                )
                .collect(Collectors.toCollection(collectionCitySupplier));
        // see 10 first results
        cityCollection.stream()
                .limit(10)
                .forEach(System.out::println);
        System.out.println(cityCollection.getClass());
    }

    // partitioning cities with distanceSeaThreshold = 200:
    // - cities with a distanceSeas <= distanceSeaThreshold
    // - cities with a distanceSeas > distanceSeaThreshold
    @ParameterizedTest
    @ValueSource(ints={100, 200, 300})
    void demoCollectosPArtition(int distanceSeaThreshold){
        Map<Boolean, List<City>> cityPartitioning = cities.parallelStream()
                .collect(Collectors.partitioningBy(
                        city -> city.getDistanceSea() <= distanceSeaThreshold));
        cityPartitioning.forEach((isDistanceShort, cityCollection) -> {
            System.out.println(
                    "distance to sea "
                            + (isDistanceShort ? "<= " : "> ")
                            + distanceSeaThreshold
                            + ":");
            cityCollection.forEach(city -> System.out.println(
                    "\t - "
                            + city.getName()
                            + "("
                            + city.getDistanceSea()
                            + " km)"
            ));
        });
    }

    @ParameterizedTest
    @ValueSource(ints={100, 200, 300})
    void demoCollectosPartitionSorted(int distanceSeaThreshold){
        Map<Boolean, SortedSet<City>> cityPartitioning = cities.parallelStream()
                .collect(Collectors.partitioningBy(
                        city -> city.getDistanceSea() <= distanceSeaThreshold,
                        Collectors.toCollection(
                                () -> new TreeSet<>(
                                        Comparator.comparing(City::getDistanceSea)))
                        ));
        cityPartitioning.forEach((isDistanceShort, cityCollection) -> {
            System.out.println(
                    "distance to sea "
                            + (isDistanceShort ? "<= " : "> ")
                            + distanceSeaThreshold
                            + ":");
            cityCollection.forEach(city -> System.out.println(
                    "\t - "
                            + city.getName()
                            + "("
                            + city.getDistanceSea()
                            + " km)"
            ));
        });
    }
}
