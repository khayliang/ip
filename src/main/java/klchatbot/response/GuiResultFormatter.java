package klchatbot.response;

public class GuiResultFormatter implements ResultFormatter {
    @Override
    public String showWelcome() {
        return "Hello! I'm KLChatBot.\nWhat can I do for you?";
    }

    @Override
    public String showGoodbye() {
        return "Goodbye! Have a great day!";
    }

    @Override
    public String showInvalidCommand() {
        return "Sorry, I don't understand that command.";
    }

    @Override
    public String showError(String message) {
        return "An error occurred: " + message;
    }

    @Override
    public String printBox(String... lines) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            sb.append(lines[i]);
            if (i < lines.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
