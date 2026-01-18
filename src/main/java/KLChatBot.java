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
        List<Task> tasks = new ArrayList<>();

        while (true) {
            try {
                String input = stdin.readLine();
                if (input.equals("bye")) {
                    break;
                }
                if (input.equals("list")) {
                    System.out.print("____________________________________________________________\n");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.print("____________________________________________________________\n");
                } else if (input.startsWith("mark ")) {
                    int idx = Integer.parseInt(input.split(" ")[1].trim()) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        tasks.get(idx).markDone();
                        System.out.print("____________________________________________________________\n");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks.get(idx));
                        System.out.print("____________________________________________________________\n");
                    }
                } else if (input.startsWith("unmark ")) {
                    int idx = Integer.parseInt(input.split(" ")[1].trim()) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        tasks.get(idx).markUndone();
                        System.out.print("____________________________________________________________\n");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(idx));
                        System.out.print("____________________________________________________________\n");
                    }
                } else {
                    tasks.add(new Task(input));
                    System.out.print("____________________________________________________________\n");
                    System.out.println(" added: " + input);
                    System.out.print("____________________________________________________________\n");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        System.out.println(closing);
    }
}
