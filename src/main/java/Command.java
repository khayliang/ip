import java.util.List;

/**
 * Abstract base class for all commands.
 * Each command receives the tasks list through constructor injection,
 * making dependencies explicit and the command testable.
 */
public abstract class Command {
    protected List<Task> tasks;

    /**
     * Constructor that injects the tasks list dependency
     * @param tasks the shared task list that this command will operate on
     */
    public Command(List<Task> tasks) {
        this.tasks = tasks;
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

    /**
     * Helper method for consistent formatted output
     */
    protected void printBox(String... lines) {
        System.out.print("____________________________________________________________\n");
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.print("____________________________________________________________\n");
    }
}
