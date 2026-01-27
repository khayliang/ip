import java.time.LocalDateTime;

public class Deadline extends Task {
    public LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        String byStr = by.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)
            ? by.format(DateTimeParser.OUTPUT_DATE_FORMAT)
            : by.format(DateTimeParser.OUTPUT_DATETIME_FORMAT);
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + description + " (by: " + byStr + ")";
    }
}
