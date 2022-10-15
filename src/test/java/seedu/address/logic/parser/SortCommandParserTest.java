package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.sort.SortByName;


public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    // No empty arguments
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    // Invalid arguments
    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "na", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    // No Trailing or leading whitespaces
    @Test
    public void parse_validArgs_returnsSortCommand() {
        SortCommand expectedSortCommand =
                new SortCommand(new SortByName(), "name");
        assertParseSuccess(parser, "name", expectedSortCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n name \n", expectedSortCommand);
    }
}

