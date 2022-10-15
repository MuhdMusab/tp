package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Income Level in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIncome(String)}
 */
public class IncomeLevel {

    public static final String MESSAGE_CONSTRAINTS = "Income can take in any digit with a $ followed by [1-9]";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(\\$)(0|[1-9][0-9]*)$";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param income A valid address.
     */
    public IncomeLevel(String income) {
        requireNonNull(income);
        checkArgument(isValidIncome(income), MESSAGE_CONSTRAINTS);
        value = income;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidIncome(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IncomeLevel // instanceof handles nulls
                && value.equals(((IncomeLevel) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
