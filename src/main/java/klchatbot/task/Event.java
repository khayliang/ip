package klchatbot.task;

import java.time.LocalDateTime;

import klchatbot.parser.DateTimeParser;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        String fromStr = DateTimeParser.format(from);
        String toStr = DateTimeParser.format(to);
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + getDescription() + " (from: " + fromStr + " to: " + toStr + ")";
    }
}
