package klchatbot.command;

import klchatbot.task.Task;
import klchatbot.task.TaskList;
import klchatbot.response.ResultFormatter;

/**
 * Command to find tasks containing a substring in the description.
 */
public class FindCommand extends Command {
    /**
     * Constructor that receives the shared tasks list.
     */
    public FindCommand(TaskList tasks, ResultFormatter formatter) {
        super(tasks, formatter);
    }

    @Override
    public String execute(String argument) {
        String keyword = argument == null ? "" : argument.trim();
        if (keyword.isEmpty()) {
            return formatter.printBox(" Error: The find command requires a search string.");
        }

        String lowered = keyword.toLowerCase();
        StringBuilder sb = new StringBuilder();
        int matches = 0;

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.getDescription().toLowerCase().contains(lowered)) {
                if (matches > 0) {
                    sb.append("\n");
                }
                sb.append(" ").append(i + 1).append(".").append(task);
                matches++;
            }
        }

        if (matches == 0) {
            return formatter.printBox(" No matching tasks found.");
        } else {
            return formatter.printBox(" Here are the matching tasks in your list:", sb.toString());
        }
    }

    @Override
    public String getCommandName() {
        return "find";
    }
}
