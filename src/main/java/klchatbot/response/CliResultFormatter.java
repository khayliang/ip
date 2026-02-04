package klchatbot.response;

public class CliResultFormatter implements ResultFormatter {
    private static final String LINE = "____________________________________________________________";

    @Override
    public String showWelcome() {
        return "Hello! I'm KLChatBot,\n"
                + "What can I do for you?\n";
    }

    @Override
    public String showGoodbye() {
        return "\nGoodbye! Have a great day!";
    }

    @Override
    public String showInvalidCommand() {
        return printBox(" Error: Invalid command.");
    }

    @Override
    public String showError(String message) {
        return "An error occurred: " + message;
    }

    @Override
    public String printBox(String... lines) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE).append("\n");
        for (String line : lines) {
            sb.append(line).append("\n");
        }
        sb.append(LINE).append("\n");
        return sb.toString();
    }
}
