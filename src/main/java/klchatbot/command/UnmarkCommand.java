package klchatbot.command;

import klchatbot.storage.Storage;
import klchatbot.task.Task;
import klchatbot.task.TaskList;
import klchatbot.response.ResultFormatter;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public UnmarkCommand(TaskList tasks, ResultFormatter formatter) {
        super(tasks, formatter);
    }

    @Override
    public String execute(String argument) {
        try {
            int idx = Integer.parseInt(argument.trim()) - 1;

            if (idx < 0 || idx >= this.tasks.size()) {
                throw new IllegalArgumentException("Invalid task number");
            }

            // Now we can use this.tasks which was injected
            Task task = this.tasks.get(idx);
            task.markUndone();

            String response = formatter.printBox(
                " OK, I've marked this task as not done yet:",
                "   " + task
            );

            Storage.saveTasks(this.tasks);
            return response;

        } catch (Exception e) {
            return formatter.printBox(" Error: Invalid task number.");
        }
    }

    @Override
    public String getCommandName() {
        return "unmark";
    }
}
