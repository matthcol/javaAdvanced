package demo;

import data.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DemoCollections {

    @Test
    void demoSet(){
        // Set: use equals and hashCode methods
        Set<String> citySet = new HashSet<>();
        Collections.addAll(citySet, "Toulouse", "Pau", "Lyon");
        System.out.println(citySet);
        // SortedSet/NavigableSet: use Natural Order of type String
        NavigableSet<String> citySortedSet = new TreeSet<>(citySet);
        System.out.println(citySortedSet);
        // no doubles
        String city = "Pau";
        var ok1 = citySet.add(city);
        var ok2 = citySortedSet.add(city);
        System.out.println(citySet);
        System.out.println(citySortedSet);
        assertFalse(ok1, "element already present");
        assertFalse(ok2, "element already present");
        var ok3 = citySet.add("Paris");
        var ok4 = citySortedSet.add("Paris");
        System.out.println(citySet);
        System.out.println(citySortedSet);
        assertTrue(ok3, "element is new in the set");
        assertTrue(ok4, "element is new in the set");
        // api NavigableSet:
        System.out.println(citySortedSet.first());
        System.out.println(citySortedSet.last());
        System.out.println(citySortedSet.subSet("M","T"));
    }

    @Test
    void demoSortedSetAndComparators() {
        var numbers = Set.of(1,45, 22, 3, 7);
        var numbersSortedAsc = new TreeSet<>(numbers); // natural order on integers
        System.out.println(numbersSortedAsc);
        var numbersSortedDesc = new TreeSet<Integer>(Comparator.reverseOrder());
        numbersSortedDesc.addAll(numbers);
        System.out.println(numbersSortedDesc);
    }

    @Test
    void demoSortStrings() {
        var cities = Set.of("Pau", "paris", "toulouse", "TOULON", "Lyon", "lOuRdEs");
        var citiesSorted1 = new TreeSet<>(cities);
        System.out.println(citiesSorted1);
        var citiesSorted2 = new TreeSet<>(String::compareToIgnoreCase);
        citiesSorted2.addAll(cities);
        System.out.println(citiesSorted2);
    }

    @Test
    void demoOldSchoolComparator(){
        Comparator<City> cmp = new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return  o1.getName().compareTo(o2.getName());
            }
        };
        var city1 = City.builder()
                .name("Pau")
                .population(77000)
                .build();
        var city2 = City.builder()
                .name("Toulouse")
                .population(470000)
                .build();
        int resComp = cmp.compare(city1, city2);
        assertTrue(resComp < 0);
    }

    @Test
    void demoFunctionalComparator(){
        Comparator<City> cmp = Comparator.comparing(City::getName)
                .thenComparing(City::getPopulation);
        var city1 = City.builder()
                .name("Pau")
                .population(77000)
                .build();
        var city2 = City.builder()
                .name("Toulouse")
                .population(470000)
                .build();
        int resComp = cmp.compare(city1, city2);
        assertTrue(resComp < 0);
    }
}
