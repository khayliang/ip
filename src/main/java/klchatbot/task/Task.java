package klchatbot.task;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String getTypeIcon() {
        return "?";
    }

    public String getDescription() {
        return description;
    }

    @Override
    public abstract String toString();
}
