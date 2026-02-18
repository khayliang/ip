package klchatbot.parser;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DurationParser {
    private static final Pattern TOKEN = Pattern.compile(
        "(\\d+)\\s*(hours|hour|hrs|hr|h|minutes|minute|mins|min|m)",
        Pattern.CASE_INSENSITIVE
    );

    public static int parseMinutes(String input) {
        assert input != null : "input must not be null";
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Duration cannot be empty");
        }

        Matcher matcher = TOKEN.matcher(trimmed);
        int totalMinutes = 0;
        int index = 0;
        boolean isFound = false;

        while (matcher.find()) {
            isFound = true;
            if (matcher.start() != index) {
                String gap = trimmed.substring(index, matcher.start()).trim();
                if (!gap.isEmpty()) {
                    throw new IllegalArgumentException("Invalid duration format");
                }
            }
            int value = Integer.parseInt(matcher.group(1));
            String unit = matcher.group(2).toLowerCase(Locale.ROOT);
            if (unit.startsWith("h")) {
                totalMinutes += value * 60;
            } else {
                totalMinutes += value;
            }
            index = matcher.end();
        }

        if (!isFound) {
            throw new IllegalArgumentException("Invalid duration format");
        }
        if (index < trimmed.length()) {
            String tail = trimmed.substring(index).trim();
            if (!tail.isEmpty()) {
                throw new IllegalArgumentException("Invalid duration format");
            }
        }
        if (totalMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        return totalMinutes;
    }

    public static String format(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        int hours = minutes / 60;
        int mins = minutes % 60;
        if (hours > 0 && mins > 0) {
            return hours + "h " + mins + "m";
        }
        if (hours > 0) {
            return hours + "h";
        }
        return mins + "m";
    }
}
