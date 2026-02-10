package klchatbot.command;

import klchatbot.task.TaskList;
import klchatbot.response.ResultFormatter;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public ListCommand(TaskList tasks, ResultFormatter formatter) {
        super(tasks, formatter);
    }

    @Override
    public String execute(String argument) {
        return formatter.printBox(" Here are the tasks in your list:", buildTaskList());
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
