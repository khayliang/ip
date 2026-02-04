package klchatbot.response;

public interface ResultFormatter {
    String showWelcome();
    String showGoodbye();
    String showInvalidCommand();
    String showError(String message);
    String printBox(String... lines);
}
