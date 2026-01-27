public class UserInputParser {
    /**
     * Parses a raw user input string into a command name and its argument.
     *
     * @param input raw user input line
     * @return ParsedInput with command name and argument
     */
    public static ParsedInput parse(String input) {
        String commandName = input.trim();
        String argument = "";
        int firstSpace = input.indexOf(' ');
        if (firstSpace != -1) {
            commandName = input.substring(0, firstSpace).trim();
            argument = input.substring(firstSpace + 1).trim();
        }
        return new ParsedInput(commandName, argument);
    }

    public static class ParsedInput {
        private final String commandName;
        private final String argument;

        public ParsedInput(String commandName, String argument) {
            this.commandName = commandName;
            this.argument = argument;
        }

        public String getCommandName() {
            return commandName;
        }

        public String getArgument() {
            return argument;
        }
    }
}
