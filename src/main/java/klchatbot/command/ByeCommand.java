package klchatbot.command;

import klchatbot.task.TaskList;
import klchatbot.ui.Ui;

/**
 * Command to exit the application.
 */
public class ByeCommand extends Command {
    /**
     * Constructor that receives the shared tasks list (not used, but consistent interface)
     */
    public ByeCommand(TaskList tasks, Ui ui) {
        super(tasks, ui);
    }

    @Override
    public boolean execute(String argument) {
        // This command doesn't need to do anything
        return true;  // Signal to exit the application
    }

    @Override
    public String getCommandName() {
        return "bye";
    }
}
