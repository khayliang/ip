package klchatbot.command;

import klchatbot.storage.Storage;
import klchatbot.task.Task;
import klchatbot.task.TaskList;
import klchatbot.task.Todo;
import klchatbot.ui.Ui;

/**
 * Command to add a todo task.
 */
public class TodoCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public TodoCommand(TaskList tasks, Ui ui) {
        super(tasks, ui);
    }

    @Override
    public boolean execute(String argument) {
        String description = argument.trim();

        if (description.isEmpty()) {
            ui.printBox(" Error: The description of a todo cannot be empty.");
            return false;  // Don't exit the application
        }

        // Now we can use this.tasks which was injected
        Task newTask = new Todo(description);
        this.tasks.add(newTask);

        ui.printBox(
            " Got it. I've added this task:",
            "   " + newTask,
            " Now you have " + this.tasks.size() + " tasks in the list."
        );

        Storage.saveTasks(this.tasks);
        return false;  // Don't exit the application
    }

    @Override
    public String getCommandName() {
        return "todo";
    }
}
