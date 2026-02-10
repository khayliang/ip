package klchatbot.command;

import klchatbot.parser.DurationParser;
import klchatbot.response.ResultFormatter;
import klchatbot.storage.Storage;
import klchatbot.task.FixedDuration;
import klchatbot.task.Task;
import klchatbot.task.TaskList;

/**
 * Command to add a fixed-duration task.
 */
public class DurationCommand extends Command {
    public DurationCommand(TaskList tasks, ResultFormatter formatter) {
        super(tasks, formatter);
    }

    @Override
    public String execute(String argument) {
        String[] parts = argument.split(" /for ", 2);
        String description = parts[0].trim();
        String duration = parts.length > 1 ? parts[1].trim() : "";

        if (description.isEmpty() || duration.isEmpty()) {
            return formatter.printBox(" Error: The description and duration must not be empty.");
        }

        Task newTask;
        try {
            int minutes = DurationParser.parseMinutes(duration);
            newTask = new FixedDuration(description, minutes);
        } catch (IllegalArgumentException e) {
            return formatter.printBox(" Error: Invalid duration format. Use like '2h', '30m', or '1h 30m'.");
        }

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
        return "duration";
    }
}
