import java.io.BufferedReader;
import java.util.List;
import java.util.Optional;

public class KLChatBot {
    public static void main(String[] args) {
        String opening = "Hello! I'm KLChatBot,\n" +
                      "What can I do for you?\n";
        String closing = "\nGoodbye! Have a great day!";
        System.out.println(opening);

        BufferedReader stdin = new BufferedReader(new java.io.InputStreamReader(System.in));
        List<Task> tasks = Storage.loadTasks();
        CommandRegistry registry = CommandRegistry.createDefault();

        while (true) {
            try {
                String input = stdin.readLine();
                String commandName = input.trim();
                String argument = "";
                int firstSpace = input.indexOf(' ');
                if (firstSpace != -1) {
                    commandName = input.substring(0, firstSpace).trim();
                    argument = input.substring(firstSpace + 1).trim();
                }

                Optional<Command> commandOpt = registry.getCommand(commandName, tasks);
                if (!commandOpt.isPresent()) {
                    System.out.print("____________________________________________________________\n");
                    System.out.println(" Error: Invalid command.");
                    System.out.print("____________________________________________________________\n");
                    continue;
                }

                Command command = commandOpt.get();
                boolean shouldExit = command.execute(argument);
                if (shouldExit) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        System.out.println(closing);
    }
}
