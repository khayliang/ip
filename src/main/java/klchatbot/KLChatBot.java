package klchatbot;

import java.io.BufferedReader;
import java.util.Optional;

import klchatbot.command.Command;
import klchatbot.command.CommandRegistry;
import klchatbot.parser.UserInputParser;
import klchatbot.storage.Storage;
import klchatbot.task.TaskList;
import klchatbot.ui.Ui;

public class KLChatBot {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();

        BufferedReader stdin = new BufferedReader(new java.io.InputStreamReader(System.in));
        TaskList tasks = Storage.loadTasks();
        CommandRegistry registry = CommandRegistry.createDefault();

        while (true) {
            try {
                String input = stdin.readLine();
                UserInputParser.ParsedInput parsedInput = UserInputParser.parse(input);
                String commandName = parsedInput.getCommandName();
                String argument = parsedInput.getArgument();

                Optional<Command> commandOpt = registry.getCommand(commandName, tasks, ui);
                if (!commandOpt.isPresent()) {
                    ui.showInvalidCommand();
                    continue;
                }

                Command command = commandOpt.get();
                boolean shouldExit = command.execute(argument);
                if (shouldExit) {
                    break;
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }
}
