package klchatbot.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import klchatbot.KLChatBot;
import klchatbot.response.CliResultFormatter;
import klchatbot.response.ResultFormatter;

public class CliLauncher {
    public static void main(String[] args) {
        ResultFormatter formatter = new CliResultFormatter();
        KLChatBot bot = new KLChatBot(formatter);
        System.out.print(formatter.showWelcome());
        try (BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String input = stdin.readLine();
                if (input == null) {
                    break;
                }
                if (input.trim().equalsIgnoreCase("bye")) {
                    System.out.println(formatter.showGoodbye());
                    break;
                }
                String response = bot.getResponse(input);
                if (response != null && !response.isEmpty()) {
                    if (response.endsWith("\n")) {
                        System.out.print(response);
                    } else {
                        System.out.println(response);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from stdin: " + e.getMessage());
        }
    }
}
