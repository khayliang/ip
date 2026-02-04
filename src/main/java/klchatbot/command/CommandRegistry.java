package klchatbot.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import klchatbot.task.TaskList;
import klchatbot.response.ResultFormatter;

/**
 * Registry for managing command types and creating command instances.
 * Uses reflection to instantiate commands with the tasks list and UI injected.
 */
public class CommandRegistry {
    private Map<String, Class<? extends Command>> commands = new HashMap<>();

    /**
     * Registers a command type with its command name
     */
    public void register(String commandName, Class<? extends Command> commandClass) {
        commands.put(commandName, commandClass);
    }

    /**
     * Gets a command instance with the tasks list and UI injected
     * @param commandName the name of the command
     * @param tasks the shared task list to inject into the command
     * @param formatter the UI helper to inject into the command
     * @return Optional containing the command instance, or empty if command not found
     */
    public Optional<Command> getCommand(String commandName, TaskList tasks, ResultFormatter formatter) {
        Class<? extends Command> commandClass = commands.get(commandName);
        if (commandClass != null) {
            try {
                // Inject the tasks list into the command constructor
                return Optional.of(
                    commandClass.getConstructor(TaskList.class, ResultFormatter.class).newInstance(tasks, formatter)
                );
            } catch (Exception e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    /**
     * Creates a registry with all default commands registered
     */
    public static CommandRegistry createDefault() {
        CommandRegistry registry = new CommandRegistry();
        registry.register("bye", ByeCommand.class);
        registry.register("list", ListCommand.class);
        registry.register("mark", MarkCommand.class);
        registry.register("unmark", UnmarkCommand.class);
        registry.register("todo", TodoCommand.class);
        registry.register("deadline", DeadlineCommand.class);
        registry.register("event", EventCommand.class);
        registry.register("delete", DeleteCommand.class);
        registry.register("find", FindCommand.class);
        return registry;
    }
}
