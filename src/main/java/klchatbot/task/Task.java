package klchatbot.task;

public abstract class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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
