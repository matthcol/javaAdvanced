package demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.Collator;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoLocale {

    @Test
    void demoLocale(){
        System.out.println(Locale.getDefault());
        Locale.setDefault(Locale.FRENCH);
        System.out.println(Locale.getDefault());
        Locale.setDefault(Locale.of("fr", "FR"));
        System.out.println(Locale.getDefault());
        Locale.setDefault(Locale.of("es", "ES"));
        System.out.println(Locale.getDefault());
        System.out.println(Locale.getAvailableLocales().length);
        var coll = Collator.getInstance();
        assertTrue(coll.compare("mano", "mañana") < 0); // "Es: n < ñ"

    }
}
