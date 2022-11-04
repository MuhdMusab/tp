package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;
import static seedu.address.model.person.DateTime.DEFAULT_DAY_OUT_OF_BOUNDS_ERROR_MESSAGE;
import static seedu.address.model.person.DateTime.DEFAULT_MONTH_OUT_OF_BOUNDS_ERROR_MESSAGE;

import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DateTime;

/**
 * Parses input arguments and creates a new EditAppointmentCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditAppointmentCommand and returns an EditAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_DATE, PREFIX_APPOINTMENT_LOCATION);

        Index personIndex;
        Index appointmentIndex;

        try {
            String personAppointmentIndex = argMultimap.getPreamble();
            personIndex = ParserUtil.parsePersonIndex(personAppointmentIndex);
            appointmentIndex = ParserUtil.parseAppointmentIndex(personAppointmentIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE), pe);
        }
        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();


        if (argMultimap.getValue(PREFIX_APPOINTMENT_DATE).isPresent()) {
            try {
                editAppointmentDescriptor.setDateTime(
                    ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_APPOINTMENT_DATE).get()));
            } catch (DateTimeParseException e) {
                if (e.getCause() == null) {
                    throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
                }
                String str = e.getCause().getMessage();
                if (str.contains(DEFAULT_DAY_OUT_OF_BOUNDS_ERROR_MESSAGE)) {
                    throw new ParseException(DEFAULT_DAY_OUT_OF_BOUNDS_ERROR_MESSAGE);
                }
                if (str.contains(DEFAULT_MONTH_OUT_OF_BOUNDS_ERROR_MESSAGE)) {
                    throw new ParseException(DEFAULT_MONTH_OUT_OF_BOUNDS_ERROR_MESSAGE);
                }
            }
        }

        if (argMultimap.getValue(PREFIX_APPOINTMENT_LOCATION).isPresent()) {
            editAppointmentDescriptor.setLocation(
                    ParserUtil.parseLocation(argMultimap.getValue(PREFIX_APPOINTMENT_LOCATION).get()));
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE));
        }

        return new EditAppointmentCommand(personIndex, appointmentIndex, editAppointmentDescriptor);
    }
}
