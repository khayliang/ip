/**
 * Abstract base class for all commands.
 * Each command receives the tasks list and UI through constructor injection,
 * making dependencies explicit and the command testable.
 */
public abstract class Command {
    protected TaskList tasks;
    protected Ui ui;

    /**
     * Constructor that injects the tasks list and UI dependencies
     * @param tasks the shared task list that this command will operate on
     * @param ui the UI helper for output
     */
    public Command(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Executes the command with the given argument
     * @param argument the unparsed argument string after the command name
     * @return true if the application should exit, false otherwise
     */
    public abstract boolean execute(String argument);

    /**
     * Gets the command keyword (e.g., "todo", "deadline")
     */
    public abstract String getCommandName();

}
