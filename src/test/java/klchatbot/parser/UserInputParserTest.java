package klchatbot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserInputParserTest {
    @Test
    public void parse_commandOnly() {
        UserInputParser.ParsedInput parsed = UserInputParser.parse("bye");
        assertEquals("bye", parsed.getCommandName());
        assertEquals("", parsed.getArgument());
    }

    @Test
    public void parse_commandWithArgument() {
        UserInputParser.ParsedInput parsed = UserInputParser.parse("todo read book");
        assertEquals("todo", parsed.getCommandName());
        assertEquals("read book", parsed.getArgument());
    }

    @Test
    public void parse_trimsArgumentAndCommand() {
        UserInputParser.ParsedInput parsed = UserInputParser.parse("  deadline   submit report   ");
        assertEquals("deadline", parsed.getCommandName());
        assertEquals("submit report", parsed.getArgument());
    }

    @Test
    public void parse_multipleSpaces_keepsArgumentSpacingTrimmed() {
        UserInputParser.ParsedInput parsed = UserInputParser.parse("event  team sync  room-1");
        assertEquals("event", parsed.getCommandName());
        assertEquals("team sync  room-1", parsed.getArgument());
    }

    @Test
    public void parse_allWhitespace_returnsEmptyCommand() {
        UserInputParser.ParsedInput parsed = UserInputParser.parse("   ");
        assertEquals("", parsed.getCommandName());
        assertEquals("", parsed.getArgument());
    }
}
