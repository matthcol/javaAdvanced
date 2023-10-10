package demo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class DemoModernJava {

    @Test
    void demoVar() {
        // var keyword: Java 10/11
        var today = LocalDate.now();
        var cities = new ArrayList<String>();
        List<String> cities2 = new ArrayList<>(); // Java 7
        Collections.addAll(cities, "Toulouse", "Lyon", "Pau");
        cities2.addAll(cities);
        // var distance = 12; // Warning with a literal
        short distance = 12;
        for (var city: cities) {
            System.out.println(city.toUpperCase());
        }
        cities.forEach((var city) -> System.out.println(city.toLowerCase()));
    }

    @Test
    void demoCollectionConstruction() {
        // unmodifiable collections
        var cities = List.of("Toulouse", "Lyon", "Pau");
        var numbers = Set.of(12, 15, 7);
        var index = Map.of("Toulouse", 700000, "Lyon", 500000, "Pau", 77000);
        // cities.add("Paris"); // UnsupportedOperationException
        Stream.of(cities, numbers, index)
                        .forEach(object -> System.out.println(object + ": " + object.getClass()));
    }
}
