import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class DemoTimeTravel {

    @Test
    void demoDateTime() {
        var now = LocalDateTime.now();
        System.out.println(now); // 2023-10-12T09:28:12.291229900
        System.out.println("\t-year: " + now.getYear());
        System.out.println("\t-month: " + now.getMonth() + ", " + now.getMonthValue());
        System.out.println("\t-day: " + now.getDayOfMonth()
                + ", " + now.getDayOfWeek()
                + ", " + now.getDayOfYear()
        );
        var dayOfWeek = now.getDayOfWeek();
        System.out.println("Day of week (int): " + dayOfWeek.getValue());
        var datePart = now.toLocalDate();
        var timePart = now.toLocalTime();
        System.out.println("Date/time: " + datePart + " / " + timePart);
    }

    @Test
    void demoDateTimeWithTimeZone() {
        var tzVietnam = ZoneId.of("Asia/Ho_Chi_Minh");
        var dtVietnam = LocalDateTime.now(tzVietnam);
        var zdtVietnam = ZonedDateTime.now(tzVietnam);
        System.out.println("Local dt Vietnam: " + dtVietnam);
        System.out.println("Zoned dt Vietnam: " + zdtVietnam);
    }


    @ParameterizedTest
    @ValueSource(strings={
            "Asia/Ho_Chi_Minh",
            "Europe/Paris",
            "Europe/London",
            "Europe/Warsaw",
            "Europe/Lisbon",
            "Pacific/Pago_Pago",
            "America/Los_Angeles",
            "America/New_York",
            "Pacific/Honolulu",
            "Asia/Tehran"
    })
    void demoDateTimeWithTimeZone2(String zoneIdStr) {
        var zoneId = ZoneId.of(zoneIdStr);
        var dt = LocalDateTime.now(zoneId);
        var zdt = ZonedDateTime.now(zoneId);
        System.out.println(zoneId);
        System.out.println("\t-Local dt: " + dt);
        System.out.println("\t-Zoned dt: " + zdt);
    }

    @Test
    void demoAllZoneId() {
        ZoneId.getAvailableZoneIds()
                .stream()
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    void demoBuild(){
        var d = LocalDate.of(2024, 2, 29);
        System.out.println(d);
        var dt1 = LocalDateTime.of(2400, 2, 29, 12, 45);
        var dt2 = LocalDateTime.of(2400, 2, 29, 12, 45, 25);
        var dt3 = LocalDateTime.of(2400, 2, 29, 12, 45, 25, 125);
        Stream.of(dt1, dt2, dt3)
                .forEach(System.out::println);
    }

    @Test
    void demoFormat(){
        var dateStrISO = "2024-02-29";
        var d = LocalDate.parse(dateStrISO);
        System.out.println(d);
        var dateStrFr = "20/02/2024";
        var d2 = LocalDate.parse(dateStrFr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println(d2);
        var formatFrCustom = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
        var dt = LocalDateTime.parse("29/02/2024 12:35:15.123", formatFrCustom);
        System.out.println(dt);
        System.out.println(dt.format(formatFrCustom));
    }

    @Test
    void demoComputeWithTemporalData(){
        var dt = LocalDateTime.now();
        var dt2 = dt.plusMinutes(15);
        System.out.println(dt2);

        var d31 = LocalDate.of(2024,1,31);
        var d31nextMonth = d31.plusMonths(1);
        System.out.println(d31nextMonth);

        var d3 = dt.plus(Duration.ofDays(3).plusHours(3));
        System.out.println(d3);

        var d4 = dt.plus(Duration.parse("P3DT50H"));
        System.out.println(d4);
    }

}
