package klchatbot.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter INPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    public static class DateTimeInfo {
        private final LocalDateTime dateTime;
        private final String originalString;

        public DateTimeInfo(LocalDateTime dateTime, String originalString) {
            this.dateTime = dateTime;
            this.originalString = originalString;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public String getOriginalString() {
            return originalString;
        }

        @Override
        public String toString() {
            return format(dateTime);
        }
    }

    /**
     * Parses a date/time string in the format yyyy-MM-dd or yyyy-MM-dd HHmm
     * If only date is provided, time defaults to 00:00
     *
     * @param dateTimeString the string to parse
     * @return DateTimeInfo object with parsed LocalDateTime
     * @throws DateTimeParseException if the string cannot be parsed
     */
    public static DateTimeInfo parse(String dateTimeString) throws DateTimeParseException {
        assert dateTimeString != null : "dateTimeString must not be null";
        String trimmed = dateTimeString.trim();

        try {
            LocalDateTime dateTime = LocalDateTime.parse(trimmed, INPUT_DATETIME_FORMAT);
            return new DateTimeInfo(dateTime, trimmed);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate date = LocalDate.parse(trimmed, INPUT_DATE_FORMAT);
                LocalDateTime dateTime = date.atStartOfDay();
                return new DateTimeInfo(dateTime, trimmed);
            } catch (DateTimeParseException e2) {
                throw new DateTimeParseException("Invalid date/time format. Expected: yyyy-MM-dd or yyyy-MM-dd HHmm", trimmed, 0);
            }
        }
    }

    public static String format(LocalDateTime dateTime) {
        if (dateTime.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            return dateTime.format(OUTPUT_DATE_FORMAT);
        }
        return dateTime.format(OUTPUT_DATETIME_FORMAT);
    }
}
