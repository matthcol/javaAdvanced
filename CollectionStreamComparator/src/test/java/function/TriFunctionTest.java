package function;

import data.City;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriFunctionTest {

    @Test
    void testTriFunction() {
        TriFunction<Integer,Integer,Integer,?> f1 = (x,y,z) -> x + y + z;
        TriFunction<String,Integer,Integer,City> f2 = (name,  pop,  dist) -> City.builder()
                .name(name)
                .population(pop)
                .distanceSea(dist)
                .build();
        System.out.println(f1.apply(3,4,5));
        System.out.println(f2.apply("Toulouse", 477000, 150));
    }

}