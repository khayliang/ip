package klchatbot.task;

import klchatbot.parser.DurationParser;

public class FixedDuration extends Task {
    private final int durationMinutes;

    public FixedDuration(String description, int durationMinutes) {
        super(description);
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("durationMinutes must be positive");
        }
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public String getTypeIcon() {
        return "F";
    }

    @Override
    public String toString() {
        String durationStr = DurationParser.format(durationMinutes);
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + getDescription() + " (for: " + durationStr + ")";
    }
}
