import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructor that receives the shared tasks list
     */
    public DeadlineCommand(List<Task> tasks) {
        super(tasks);
    }

    @Override
    public boolean execute(String argument) {
        String[] parts = argument.split(" /by ", 2);
        String desc = parts[0].trim();
        String by = parts.length > 1 ? parts[1].trim() : "";

        if (desc.isEmpty() || by.isEmpty()) {
            printBox(" Error: The description and deadline must not be empty.");
            return false;  // Don't exit the application
        }

        Task newTask;
        try {
            DateTimeParser.DateTimeInfo dateTimeInfo = DateTimeParser.parse(by);
            newTask = new Deadline(desc, dateTimeInfo.dateTime);
        } catch (DateTimeParseException e) {
            printBox(" Error: Invalid date format. Please use yyyy-MM-dd or yyyy-MM-dd HHmm");
            return false;  // Don't exit the application
        }

        this.tasks.add(newTask);

        printBox(
            " Got it. I've added this task:",
            "   " + newTask,
            " Now you have " + this.tasks.size() + " tasks in the list."
        );

        Storage.saveTasks(this.tasks);
        return false;  // Don't exit the application
    }

    @Override
    public String getCommandName() {
        return "deadline";
    }
}
