import java.util.List;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public UnmarkCommand(List<Task> tasks) {
        super(tasks);
    }

    @Override
    public boolean execute(String argument) {
        try {
            int idx = Integer.parseInt(argument.trim()) - 1;

            if (idx < 0 || idx >= this.tasks.size()) {
                throw new IllegalArgumentException("Invalid task number");
            }

            // Now we can use this.tasks which was injected
            Task task = this.tasks.get(idx);
            task.markUndone();

            printBox(
                " OK, I've marked this task as not done yet:",
                "   " + task
            );

            Storage.saveTasks(this.tasks);
            return false;  // Don't exit the application

        } catch (Exception e) {
            printBox(" Error: Invalid task number.");
            return false;  // Don't exit the application
        }
    }

    @Override
    public String getCommandName() {
        return "unmark";
    }
}
