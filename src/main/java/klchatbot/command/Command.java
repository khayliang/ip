/**
 * Abstract base class for all commands.
 * Each command receives the tasks list and UI through constructor injection,
 * making dependencies explicit and the command testable.
 */
package klchatbot.command;

import klchatbot.task.TaskList;
import klchatbot.response.ResultFormatter;

public abstract class Command {
    protected TaskList tasks;
    protected ResultFormatter formatter;

    /**
     * Constructor that injects the tasks list and UI dependencies
     * @param tasks the shared task list that this command will operate on
     * @param formatter the response helper for formatting
     */
    public Command(TaskList tasks, ResultFormatter formatter) {
        this.tasks = tasks;
        this.formatter = formatter;
    }

    /**
     * Executes the command with the given argument
     * @param argument the unparsed argument string after the command name
     * @return response string to show the user
     */
    public abstract String execute(String argument);

    /**
     * Gets the command keyword (e.g., "todo", "deadline")
     */
    public abstract String getCommandName();

}
