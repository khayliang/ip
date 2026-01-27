package klchatbot.task;

import java.time.LocalDateTime;

import klchatbot.parser.DateTimeParser;

public class Event extends Task {
    public LocalDateTime from;
    public LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        String fromStr = from.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)
            ? from.format(DateTimeParser.OUTPUT_DATE_FORMAT)
            : from.format(DateTimeParser.OUTPUT_DATETIME_FORMAT);
        String toStr = to.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)
            ? to.format(DateTimeParser.OUTPUT_DATE_FORMAT)
            : to.format(DateTimeParser.OUTPUT_DATETIME_FORMAT);
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + description + " (from: " + fromStr + " to: " + toStr + ")";
    }
}
