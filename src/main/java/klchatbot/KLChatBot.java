package klchatbot;

import java.util.Optional;

import klchatbot.command.Command;
import klchatbot.command.CommandRegistry;
import klchatbot.parser.UserInputParser;
import klchatbot.response.ResultFormatter;
import klchatbot.storage.Storage;
import klchatbot.task.TaskList;

public class KLChatBot {
    private final ResultFormatter formatter;
    private final TaskList tasks;
    private final CommandRegistry registry;

    public KLChatBot(ResultFormatter formatter) {
        this.formatter = formatter;
        this.tasks = Storage.loadTasks();
        this.registry = CommandRegistry.createDefault();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            UserInputParser.ParsedInput parsedInput = UserInputParser.parse(input);
            String commandName = parsedInput.getCommandName();
            String argument = parsedInput.getArgument();

            Optional<Command> commandOpt = registry.getCommand(commandName, tasks, formatter);
            if (commandOpt.isEmpty()) {
                return formatter.showInvalidCommand();
            }

            Command command = commandOpt.get();
            return command.execute(argument);
        } catch (Exception e) {
            return formatter.showError(e.getMessage());
        }
    }
}
