package klchatbot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTimeParserTest {
    @Test
    public void parse_dateOnly_returnsStartOfDay() {
        DateTimeParser.DateTimeInfo info = DateTimeParser.parse("2024-01-05");
        assertEquals(LocalDateTime.of(2024, 1, 5, 0, 0), info.getDateTime());
        assertEquals("2024-01-05", info.getOriginalString());
    }

    @Test
    public void parse_dateTime_returnsExactTime() {
        DateTimeParser.DateTimeInfo info = DateTimeParser.parse("2024-12-31 2359");
        assertEquals(LocalDateTime.of(2024, 12, 31, 23, 59), info.getDateTime());
        assertEquals("2024-12-31 2359", info.getOriginalString());
    }

    @Test
    public void parse_trimsWhitespace() {
        DateTimeParser.DateTimeInfo info = DateTimeParser.parse(" 2024-02-01 0930 ");
        assertEquals(LocalDateTime.of(2024, 2, 1, 9, 30), info.getDateTime());
        assertEquals("2024-02-01 0930", info.getOriginalString());
    }

    @Test
    public void parse_invalidFormat_throws() {
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.parse("01-02-2024"));
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.parse("2024/01/05"));
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.parse("2024-13-40"));
    }

    @Test
    public void dateTimeInfo_toString_formatsDateOnly() {
        DateTimeParser.DateTimeInfo info = new DateTimeParser.DateTimeInfo(
                LocalDateTime.of(2024, 3, 2, 0, 0), "2024-03-02");
        assertEquals("Mar 02 2024", info.toString());
    }

    @Test
    public void dateTimeInfo_toString_formatsDateTime() {
        DateTimeParser.DateTimeInfo info = new DateTimeParser.DateTimeInfo(
                LocalDateTime.of(2024, 3, 2, 9, 5), "2024-03-02 0905");
        assertEquals("Mar 02 2024 0905", info.toString());
    }
}
