package klchatbot.command;

import klchatbot.storage.Storage;
import klchatbot.task.Task;
import klchatbot.task.TaskList;
import klchatbot.task.Todo;
import klchatbot.response.ResultFormatter;

/**
 * Command to add a todo task.
 */
public class TodoCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public TodoCommand(TaskList tasks, ResultFormatter formatter) {
        super(tasks, formatter);
    }

    @Override
    public String execute(String argument) {
        String description = argument.trim();

        if (description.isEmpty()) {
            return formatter.printBox(" Error: The description of a todo cannot be empty.");
        }

        // Now we can use this.tasks which was injected
        Task newTask = new Todo(description);
        this.tasks.add(newTask);

        String response = formatter.printBox(
            " Got it. I've added this task:",
            "   " + newTask,
            " Now you have " + this.tasks.size() + " tasks in the list."
        );

        Storage.saveTasks(this.tasks);
        return response;
    }

    @Override
    public String getCommandName() {
        return "todo";
    }
}
