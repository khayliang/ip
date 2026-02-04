package klchatbot.command;

import klchatbot.storage.Storage;
import klchatbot.task.Task;
import klchatbot.task.TaskList;
import klchatbot.response.ResultFormatter;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public MarkCommand(TaskList tasks, ResultFormatter formatter) {
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
            task.markDone();

            String response = formatter.printBox(
                " Nice! I've marked this task as done:",
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
        return "mark";
    }
}
