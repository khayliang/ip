package klchatbot.command;

import klchatbot.task.TaskList;
import klchatbot.response.ResultFormatter;

/**
 * Command to exit the application.
 */
public class ByeCommand extends Command {
    /**
     * Constructor that receives the shared tasks list (not used, but consistent interface)
     */
    public ByeCommand(TaskList tasks, ResultFormatter formatter) {
        super(tasks, formatter);
    }

    @Override
    public String execute(String argument) {
        return formatter.showGoodbye();
    }

    @Override
    public String getCommandName() {
        return "bye";
    }
}
