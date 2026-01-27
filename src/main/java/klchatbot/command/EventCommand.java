package klchatbot.command;

import java.time.format.DateTimeParseException;

import klchatbot.parser.DateTimeParser;
import klchatbot.storage.Storage;
import klchatbot.task.Event;
import klchatbot.task.Task;
import klchatbot.task.TaskList;
import klchatbot.ui.Ui;
/**
 * Command to add an event task.
 */
public class EventCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public EventCommand(TaskList tasks, Ui ui) {
        super(tasks, ui);
    }

    @Override
    public boolean execute(String argument) {
        String[] eventParts = argument.split(" /from ", 2);
        String eventDesc = eventParts[0].trim();
        String from = "", to = "";

        if (eventParts.length > 1) {
            String[] fromTo = eventParts[1].split(" /to ", 2);
            from = fromTo[0].trim();
            if (fromTo.length > 1) {
                to = fromTo[1].trim();
            }
        }

        if (eventDesc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            ui.printBox(" Error: The description, from, and to fields must not be empty.");
            return false;  // Don't exit the application
        }

        Task newTask;
        try {
            DateTimeParser.DateTimeInfo fromDateTime = DateTimeParser.parse(from);
            DateTimeParser.DateTimeInfo toDateTime = DateTimeParser.parse(to);
            newTask = new Event(eventDesc, fromDateTime.dateTime, toDateTime.dateTime);
        } catch (DateTimeParseException e) {
            ui.printBox(" Error: Invalid date format. Please use yyyy-MM-dd or yyyy-MM-dd HHmm");
            return false;  // Don't exit the application
        }

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
        return "event";
    }
}
