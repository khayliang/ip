import java.io.BufferedReader;
import java.util.List;
import java.util.Optional;

public class KLChatBot {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();

        BufferedReader stdin = new BufferedReader(new java.io.InputStreamReader(System.in));
        List<Task> tasks = Storage.loadTasks();
        CommandRegistry registry = CommandRegistry.createDefault();

        while (true) {
            try {
                String input = stdin.readLine();
                String commandName = input.trim();
                String argument = "";
                int firstSpace = input.indexOf(' ');
                if (firstSpace != -1) {
                    commandName = input.substring(0, firstSpace).trim();
                    argument = input.substring(firstSpace + 1).trim();
                }

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
