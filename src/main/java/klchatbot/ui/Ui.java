package klchatbot.ui;

public class Ui {
    private static final String LINE = "____________________________________________________________";

    public void showWelcome() {
        System.out.println("Hello! I'm KLChatBot,");
        System.out.println("What can I do for you?");
        System.out.println();
    }

    public void showGoodbye() {
        System.out.println();
        System.out.println("Goodbye! Have a great day!");
    }

    public void showInvalidCommand() {
        printBox(" Error: Invalid command.");
    }

    public void showError(String message) {
        System.out.println("An error occurred: " + message);
    }

    public void printBox(String... lines) {
        System.out.print(LINE + "\n");
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.print(LINE + "\n");
    }
}
