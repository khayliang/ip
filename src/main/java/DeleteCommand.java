import java.util.List;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public DeleteCommand(List<Task> tasks) {
        super(tasks);
    }

    @Override
    public boolean execute(String argument) {
        try {
            int idx = Integer.parseInt(argument.trim()) - 1;

            if (idx < 0 || idx >= this.tasks.size()) {
                throw new IllegalArgumentException("Invalid task number");
            }

            Task removed = this.tasks.remove(idx);

            printBox(
                " Noted. I've removed this task:",
                "   " + removed,
                " Now you have " + this.tasks.size() + " tasks in the list."
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
        return "delete";
    }
}
