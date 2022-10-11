package seedu.address.logic.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses string representation of a date and time and creates
 * a new LocalDateTime object
 */
public class DateTimeParser {
    private static final String DATE_TIME_FORMAT = "d-MMM-yyyy hh:mm a";
    private static final String DATE_FORMAT = "d-MMM-yyyy";
    private static final DateTimeFormatter dateTimeFormatter = java.time.format
            .DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    private static final DateTimeFormatter dateFormatter = java.time.format
            .DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Parses the given {@code String} representing a date and time
     * and returns a LocalDateTime object.
     *
     * @param str String representing the LocalDateTime to be returned.
     * @return LocalDateTime parsed from the input String.
     */
    public static LocalDateTime parseLocalDateTimeFromString(String str) {
        return LocalDateTime.parse(str, DateTimeParser.dateTimeFormatter);
    }

    /**
     * Parses the given {@code String} representing a date
     * and returns a LocalDate object.
     *
     * @param str String representing the LocalDate to be returned.
     * @return LocalDate parsed from the input String.
     */
    public static LocalDate parseLocalDateFromString(String str) {
        return LocalDate.parse(str, DateTimeParser.dateFormatter);
    }

    /**
     * Checks whether the input String has the correct DateTime format.
     *
     * @param str the String representing the LocalDateTime to be parsed.
     * @return boolean value describing whether the input String has
     *         the correct DateTime format.
     */
    public static boolean isValidDateTime(String str) {
        try {
            parseLocalDateTimeFromString(str);
        } catch (DateTimeParseException exception) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether the input String has the correct Date format.
     *
     * @param str the String representing the LocalDate to be parsed.
     * @return boolean value describing whether the input String has
     *         the correct Date format.
     */
    public static boolean isValidDate(String str) {
        try {
            parseLocalDateFromString(str);
        } catch (DateTimeParseException exception) {
            return false;
        }
        return true;
    }
}
