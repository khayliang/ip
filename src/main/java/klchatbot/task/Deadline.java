package klchatbot.task;

import java.time.LocalDateTime;

import klchatbot.parser.DateTimeParser;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        String byStr = DateTimeParser.format(by);
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + getDescription() + " (by: " + byStr + ")";
    }
}
