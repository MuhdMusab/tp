package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.DateTimeParser;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.DateTime;

import static seedu.address.model.person.Appointment.MESSAGE_CONSTRAINTS;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {
    private final String appointmentName;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given {@code appointmentName}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(String appointmentName) {
        this.appointmentName = appointmentName;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointmentName = source.toString();
    }

    @JsonValue
    public String getAppointmentName() {
        return appointmentName;
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     */
    public Appointment toModelType() throws IllegalValueException {
        String dateAndTime = appointmentName.trim();
        if (!DateTimeParser.isValidDateTime(dateAndTime)) {
            throw new IllegalValueException(String.format(MESSAGE_CONSTRAINTS, Appointment.class.getSimpleName()));
        }
        LocalDateTime localDateTime = DateTimeParser.parseLocalDateTimeFromString(dateAndTime.trim());
        DateTime dateTime = new DateTime(localDateTime);
        return new Appointment(dateTime);
    }

}
