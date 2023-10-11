package data.demo;

import org.junit.jupiter.api.Test;

public class DemoMultilineStrings {
    static final String QUERY = """
            SELECT
                m.title,
                m.year
            FROM
                movie m
            WHERE
                m.year BETWEEN 2000 AND 2009
            ORDER BY m.year DESC, m.title
            """;

    @Test
    void demoMultiline() {
        System.out.println(QUERY);
    }


}
