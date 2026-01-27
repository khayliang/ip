import java.util.List;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public MarkCommand(List<Task> tasks, Ui ui) {
        super(tasks, ui);
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
            task.markDone();

            ui.printBox(
                " Nice! I've marked this task as done:",
                "   " + task
            );

            Storage.saveTasks(this.tasks);
            return false;  // Don't exit the application

        } catch (Exception e) {
            ui.printBox(" Error: Invalid task number.");
            return false;  // Don't exit the application
        }
    }

    @Override
    public String getCommandName() {
        return "mark";
    }
}
