package klchatbot.command;

import java.time.format.DateTimeParseException;

import klchatbot.parser.DateTimeParser;
import klchatbot.storage.Storage;
import klchatbot.task.Deadline;
import klchatbot.task.Task;
import klchatbot.task.TaskList;
import klchatbot.response.ResultFormatter;
/**
 * Command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public DeadlineCommand(TaskList tasks, ResultFormatter formatter) {
        super(tasks, formatter);
    }

    @Override
    public String execute(String argument) {
        String[] parts = argument.split(" /by ", 2);
        String desc = parts[0].trim();
        String by = parts.length > 1 ? parts[1].trim() : "";

        if (desc.isEmpty() || by.isEmpty()) {
            return formatter.printBox(" Error: The description and deadline must not be empty.");
        }

        Task newTask;
        try {
            DateTimeParser.DateTimeInfo dateTimeInfo = DateTimeParser.parse(by);
            newTask = new Deadline(desc, dateTimeInfo.getDateTime());
        } catch (DateTimeParseException e) {
            return formatter.printBox(" Error: Invalid date format. Please use yyyy-MM-dd or yyyy-MM-dd HHmm");
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
        return "deadline";
    }
}
