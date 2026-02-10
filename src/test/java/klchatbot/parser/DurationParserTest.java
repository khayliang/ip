package klchatbot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationParserTest {
    @Test
    public void parseMinutes_hours_returnsMinutes() {
        assertEquals(120, DurationParser.parseMinutes("2 hours"));
        assertEquals(60, DurationParser.parseMinutes("1h"));
    }

    @Test
    public void parseMinutes_mixedUnits_sumsMinutes() {
        assertEquals(90, DurationParser.parseMinutes("1h 30m"));
        assertEquals(135, DurationParser.parseMinutes("2h15m"));
    }

    @Test
    public void parseMinutes_minutesOnly_returnsMinutes() {
        assertEquals(45, DurationParser.parseMinutes("45 minutes"));
        assertEquals(30, DurationParser.parseMinutes("30m"));
    }

    @Test
    public void parseMinutes_invalid_throws() {
        assertThrows(IllegalArgumentException.class, () -> DurationParser.parseMinutes(""));
        assertThrows(IllegalArgumentException.class, () -> DurationParser.parseMinutes("abc"));
        assertThrows(IllegalArgumentException.class, () -> DurationParser.parseMinutes("1x"));
    }

    @Test
    public void format_formatsAsHoursOrMinutes() {
        assertEquals("2h", DurationParser.format(120));
        assertEquals("1h 30m", DurationParser.format(90));
        assertEquals("45m", DurationParser.format(45));
    }
}
