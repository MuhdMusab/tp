package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.util.StringUtil.isInteger;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;

import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Location;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddAppointmentCommand and returns an AddAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_DATE, PREFIX_APPOINTMENT_LOCATION);

        if (argMultimap.getPreamble().isEmpty()
            || !arePrefixesPresent(argMultimap, PREFIX_APPOINTMENT_DATE, PREFIX_APPOINTMENT_LOCATION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        Index personIndex;
        String oneBasedPersonIndexStr = argMultimap.getPreamble();
        if (isInteger(oneBasedPersonIndexStr) && Integer.parseInt(oneBasedPersonIndexStr) <= 0) {
            throw new ParseException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        try {
            personIndex = ParserUtil.parseIndex(oneBasedPersonIndexStr);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE), pe);
        }

        Appointment appointment = getAppointment(argMultimap);
        return new AddAppointmentCommand(personIndex, appointment);
    }

    private Appointment getAppointment(ArgumentMultimap argMultimap) throws ParseException {
        Appointment appointment;
        DateTime appointmentDateTime;
        Location appointmentLocation;

        try {
            appointmentDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_APPOINTMENT_DATE).get());
            appointmentLocation = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_APPOINTMENT_LOCATION).get());
            appointment = ParserUtil.parseAppointment(appointmentDateTime.toString(), appointmentLocation.toString());
        } catch (DateTimeParseException e) {
            if (e.getCause() == null) {
                throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
            }
            String str = e.getCause().getMessage();
            throw new ParseException(str);
        }
        return appointment;
    }
}
