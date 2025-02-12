package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnassignTaskCommand;

public class UnassignTaskCommandParserTest {
    private UnassignTaskCommandParser parser = new UnassignTaskCommandParser();
    @Test
    public void parse_allFieldsPresent_success() {
        // EP: valid inputs
        Index projectIndex = INDEX_FIRST_PROJECT;
        Index taskIndex = INDEX_FIRST_TASK;
        String userInput = " " + PREFIX_PROJECT + INDEX_FIRST_PROJECT.getOneBased() + " "
                + PREFIX_TASK + INDEX_FIRST_TASK.getOneBased();
        assertParseSuccess(parser, userInput,
                new UnassignTaskCommand(projectIndex, taskIndex));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnassignTaskCommand.MESSAGE_USAGE));

        // EP: missing project prefix
        assertParseFailure(parser,
                " " + INDEX_FIRST_PROJECT.getOneBased() + " "
                        + PREFIX_TASK + INDEX_FIRST_TASK.getOneBased() + " "
                        + PREFIX_EMPLOYEE + INDEX_FIRST_EMPLOYEE.getOneBased(),
                expectedMessage);

        // EP: missing task prefix
        assertParseFailure(parser,
                " " + PREFIX_PROJECT + INDEX_FIRST_PROJECT.getOneBased() + " "
                        + INDEX_FIRST_TASK.getOneBased() + " "
                        + PREFIX_EMPLOYEE + INDEX_FIRST_EMPLOYEE.getOneBased(),
                expectedMessage);

        // EP: all prefixes missing
        assertParseFailure(parser,
                " " + INDEX_FIRST_PROJECT.getOneBased() + " "
                        + INDEX_FIRST_TASK.getOneBased() + " "
                        + INDEX_FIRST_EMPLOYEE.getOneBased(),
                expectedMessage);
    }
}
