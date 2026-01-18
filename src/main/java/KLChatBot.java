import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class KLChatBot {
    public static void main(String[] args) {
        String opening = "Hello! I'm KLChatBot,\n" +
                      "What can I do for you?\n";
        String closing = "\nGoodbye! Have a great day!";
        System.out.println(opening);

        BufferedReader stdin = new BufferedReader(new java.io.InputStreamReader(System.in));
        List<String> userCommands = new ArrayList<>();

        while (true) {
            try {
                String input = stdin.readLine();
                if (input.equals("bye")) {
                    break;
                }
                if (input.equals("list")) {
                    System.out.print("____________________________________________________________\n");
                    for (int i = 0; i < userCommands.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + userCommands.get(i));
                    }
                } else {
                    userCommands.add(input);
                    System.out.print("____________________________________________________________\n");
                    System.out.println(" added: " + input);
                }
                System.out.print("____________________________________________________________\n");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        System.out.println(closing);
    }
}
