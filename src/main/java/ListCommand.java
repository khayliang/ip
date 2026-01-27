import java.util.List;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public ListCommand(List<Task> tasks) {
        super(tasks);
    }

    @Override
    public boolean execute(String argument) {
        printBox(" Here are the tasks in your list:", buildTaskList());
        return false;  // Don't exit the application
    }

    @Override
    public String getCommandName() {
        return "list";
    }

    /**
     * Helper method to build the task list string
     */
    private String buildTaskList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(this.tasks.get(i));
            if (i < this.tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
