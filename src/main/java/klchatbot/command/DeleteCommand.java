package klchatbot.command;

import klchatbot.storage.Storage;
import klchatbot.task.Task;
import klchatbot.task.TaskList;
import klchatbot.response.ResultFormatter;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public DeleteCommand(TaskList tasks, ResultFormatter formatter) {
        super(tasks, formatter);
    }

    @Override
    public String execute(String argument) {
        try {
            int idx = Integer.parseInt(argument.trim()) - 1;

            if (idx < 0 || idx >= this.tasks.size()) {
                throw new IllegalArgumentException("Invalid task number");
            }

            Task removed = this.tasks.remove(idx);

            String response = formatter.printBox(
                " Noted. I've removed this task:",
                "   " + removed,
                " Now you have " + this.tasks.size() + " tasks in the list."
            );

            Storage.saveTasks(this.tasks);
            return response;

        } catch (Exception e) {
            return formatter.printBox(" Error: Invalid task number.");
        }
    }

    @Override
    public String getCommandName() {
        return "delete";
    }
}
