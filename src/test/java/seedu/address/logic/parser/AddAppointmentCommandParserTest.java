package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SECOND_APPOINTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_22_JAN_2023;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresentOneAppointment_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + FIRST_APPOINTMENT_DESC;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withAppointments(VALID_APPOINTMENT_21_JAN_2023).build();
        AddAppointmentCommand expectedCommand = new AddAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsPresentMultipleAppointments_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + FIRST_APPOINTMENT_DESC + SECOND_APPOINTMENT_DESC;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withAppointments(VALID_APPOINTMENT_21_JAN_2023, VALID_APPOINTMENT_22_JAN_2023).build();
        AddAppointmentCommand expectedCommand = new AddAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidAppointmentField_failure() {
        String expectedFailureMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE);
        int targetIndex = INDEX_SECOND_PERSON.getOneBased();

<<<<<<< HEAD
        try {
            parser.parse(userInput);
            fail();
        } catch (ParseException parseException) {
            assertEquals("Invalid command format! \naddappt: Schedules an appointment with "
                    + "a specific client by the index number used in the displayed person list. "
                    + "Parameters: INDEX (must be a positive integer) d/DATE_TIME [d/DATE_TIME]...\n"
                    + "Example: addappt 1 d/21-Jan-2023 12:30 PM " , parseException.getMessage());
        }
=======
        // add appointment with invalid date
        assertParseFailure(parser, targetIndex + INVALID_APPOINTMENT_DESC, expectedFailureMessage);

        // add appointment with no field
        assertParseFailure(parser, targetIndex + "", expectedFailureMessage);

        // add multiple appointments with invalid field in one of them
        assertParseFailure(parser,
                targetIndex + FIRST_APPOINTMENT_DESC + INVALID_APPOINTMENT_DESC, expectedFailureMessage);
>>>>>>> master
    }

    @Test
    public void parse_validAppointmentWithOtherField_failure() {
        String expectedFailureMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE);
        int targetIndex = INDEX_SECOND_PERSON.getOneBased();

<<<<<<< HEAD
        try {
            parser.parse(userInput);
            fail();
        } catch (ParseException parseException) {
            assertEquals("Invalid command format! \naddappt: Schedules an appointment with "
                    + "a specific client by the index number used in the displayed person list. "
                    + "Parameters: INDEX (must be a positive integer) d/DATE_TIME [d/DATE_TIME]...\n"
                    + "Example: addappt 1 d/21-Jan-2023 12:30 PM " , parseException.getMessage());
        }
    }
=======
        // add appointment with tag
        assertParseFailure(parser, targetIndex + TAG_DESC_FRIEND + FIRST_APPOINTMENT_DESC, expectedFailureMessage);
>>>>>>> master

        // add appointment with phone
        assertParseFailure(parser, targetIndex + PHONE_DESC_AMY + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

<<<<<<< HEAD
        try {
            parser.parse(userInput);
            fail();
        } catch (ParseException parseException) {
            assertEquals("Invalid command format! \naddappt: Schedules an appointment with "
                    + "a specific client by the index number used in the displayed person list. "
                    + "Parameters: INDEX (must be a positive integer) d/DATE_TIME [d/DATE_TIME]...\n"
                    + "Example: addappt 1 d/21-Jan-2023 12:30 PM " , parseException.getMessage());
        }
    }

    @Test
    public void parse_validAppointmentWithEmail_failure() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY + FIRST_APPOINTMENT_DESC;

        try {
            parser.parse(userInput);
            fail();
        } catch (ParseException parseException) {
            assertEquals("Invalid command format! \naddappt: Schedules an appointment with "
                    + "a specific client by the index number used in the displayed person list. "
                    + "Parameters: INDEX (must be a positive integer) d/DATE_TIME [d/DATE_TIME]...\n"
                    + "Example: addappt 1 d/21-Jan-2023 12:30 PM " , parseException.getMessage());
        }
=======
        // add appointment with email
        assertParseFailure(parser, targetIndex + EMAIL_DESC_AMY + FIRST_APPOINTMENT_DESC, expectedFailureMessage);

>>>>>>> master
    }
}
