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
                String command = input.trim();
                String argument = "";
                int firstSpace = input.indexOf(' ');
                if (firstSpace != -1) {
                    command = input.substring(0, firstSpace).trim();
                    argument = input.substring(firstSpace + 1).trim();
                }
                switch (command) {
                    case "bye":
                        break;
                    case "list":
                        System.out.print("____________________________________________________________\n");
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                        System.out.print("____________________________________________________________\n");
                        continue;
                    case "mark":
                        try {
                            int idx = Integer.parseInt(argument) - 1;
                            if (idx < 0 || idx >= tasks.size()) {
                                throw new IllegalArgumentException();
                            }
                            tasks.get(idx).markDone();
                            System.out.print("____________________________________________________________\n");
                            System.out.println(" Nice! I've marked this task as done:");
                            System.out.println("   " + tasks.get(idx));
                            System.out.print("____________________________________________________________\n");
                        } catch (IllegalArgumentException e) {
                            System.out.print("____________________________________________________________\n");
                            System.out.println(" Error: Invalid task number.");
                            System.out.print("____________________________________________________________\n");
                        }
                        continue;
                    case "unmark":
                        try {
                            int idx = Integer.parseInt(argument) - 1;
                            if (idx < 0 || idx >= tasks.size()) {
                                throw new IllegalArgumentException();
                            }
                            tasks.get(idx).markUndone();
                            System.out.print("____________________________________________________________\n");
                            System.out.println(" OK, I've marked this task as not done yet:");
                            System.out.println("   " + tasks.get(idx));
                            System.out.print("____________________________________________________________\n");
                        } catch (IllegalArgumentException e) {
                            System.out.print("____________________________________________________________\n");
                            System.out.println(" Error: Invalid task number.");
                            System.out.print("____________________________________________________________\n");
                        }
                        continue;
                    case "todo":
                        if (argument.isEmpty()) {
                            System.out.print("____________________________________________________________\n");
                            System.out.println(" Error: The description of a todo cannot be empty.");
                            System.out.print("____________________________________________________________\n");
                        } else {
                            Task t = new Todo(argument);
                            tasks.add(t);
                            System.out.print("____________________________________________________________\n");
                            System.out.println(" Got it. I've added this task:");
                            System.out.println("   " + t);
                            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                            System.out.print("____________________________________________________________\n");
                        }
                        continue;
                    case "deadline":
                        String[] parts = argument.split(" /by ", 2);
                        String desc = parts[0].trim();
                        String by = parts.length > 1 ? parts[1].trim() : "";
                        if (desc.isEmpty() || by.isEmpty()) {
                            System.out.print("____________________________________________________________\n");
                            System.out.println(" Error: The description and deadline must not be empty.");
                            System.out.print("____________________________________________________________\n");
                        } else {
                            Task t = new Deadline(desc, by);
                            tasks.add(t);
                            System.out.print("____________________________________________________________\n");
                            System.out.println(" Got it. I've added this task:");
                            System.out.println("   " + t);
                            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                            System.out.print("____________________________________________________________\n");
                        }
                        continue;
                    case "event":
                        String[] eventParts = argument.split(" /from ", 2);
                        String eventDesc = eventParts[0].trim();
                        String from = "", to = "";
                        if (eventParts.length > 1) {
                            String[] fromTo = eventParts[1].split(" /to ", 2);
                            from = fromTo[0].trim();
                            if (fromTo.length > 1) {
                                to = fromTo[1].trim();
                            }
                        }
                        if (eventDesc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                            System.out.print("____________________________________________________________\n");
                            System.out.println(" Error: The description, from, and to fields must not be empty.");
                            System.out.print("____________________________________________________________\n");
                        } else {
                            Task t = new Event(eventDesc, from, to);
                            tasks.add(t);
                            System.out.print("____________________________________________________________\n");
                            System.out.println(" Got it. I've added this task:");
                            System.out.println("   " + t);
                            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                            System.out.print("____________________________________________________________\n");
                        }
                        continue;
                    case "delete":
                        try {
                            int idx = Integer.parseInt(argument) - 1;
                            if (idx < 0 || idx >= tasks.size()) {
                                throw new IllegalArgumentException();
                            }
                            Task removed = tasks.remove(idx);
                            System.out.print("____________________________________________________________\n");
                            System.out.println(" Noted. I've removed this task:");
                            System.out.println("   " + removed);
                            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                            System.out.print("____________________________________________________________\n");
                        } catch (IllegalArgumentException e) {
                            System.out.print("____________________________________________________________\n");
                            System.out.println(" Error: Invalid task number.");
                            System.out.print("____________________________________________________________\n");
                        }
                        continue;
                    default:
                        System.out.print("____________________________________________________________\n");
                        System.out.println(" Error: Invalid command.");
                        System.out.print("____________________________________________________________\n");
                        continue;
                }

                // we can break here because all other commands use a continue
                // so only time we exit switch statement is when command is "bye"
                break;
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        System.out.println(closing);
    }
}
