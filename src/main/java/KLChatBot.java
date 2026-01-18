import java.io.BufferedReader;

public class KLChatBot {
    public static void main(String[] args) {
        String opening = "Hello! I'm KLChatBot,\n" +
                      "What can I do for you?\n";
        String closing = "\nGoodbye! Have a great day!";
        System.out.println(opening);

        BufferedReader stdin = new BufferedReader(new java.io.InputStreamReader(System.in));

        while (true) {
            try {
                String input = stdin.readLine();
                if (input.equals("bye")) {
                    break;
                }
                System.out.print("____________________________________________________________\n");
                System.out.println(input);
                System.out.print("____________________________________________________________\n");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        System.out.println(closing);
    }
}
