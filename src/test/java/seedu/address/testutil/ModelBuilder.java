package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;

import seedu.address.model.AddressBook;
import seedu.address.model.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * A utility class to help with building Appointment objects.
 */
public class ModelBuilder {
    private AddressBook addressBook =
                    new AddressBookBuilder()
                    .withPerson(new PersonBuilder()
                            .withName("Alice Pauline")
                            .withAddress("123, Jurong West Ave 6, #08-111")
                            .withEmail("alice@example.com")
                            .withPhone("94351253")
                            .withIncome("1000")
                            .withMonthly("200")
                            .withRiskTag("LOW")
                            .withPlanTag("Savings Plan")
                            .withClientTag("POTENTIAL")
                            .withTags("friends")
                            .build())
                    .withPerson(new PersonBuilder()
                            .withName("Benson Meier")
                            .withAddress("311, Clementi Ave 2, #02-25")
                            .withEmail("johnd@example.com")
                            .withPhone("98765432")
                            .withIncome("1000")
                            .withMonthly("100")
                            .withRiskTag("HIGH")
                            .withPlanTag("Savings Plan")
                            .withClientTag("POTENTIAL")
                            .withTags("owesMoney", "friends")
                            .build())
                    .withPerson(new PersonBuilder()
                            .withName("Carl Kurz")
                            .withPhone("95352563")
                            .withEmail("heinz@example.com")
                            .withAddress("wall street")
                            .withIncome("1000")
                            .withMonthly("200")
                            .withRiskTag("LOW")
                            .withPlanTag("Savings Plan")
                            .withClientTag("CURRENT")
                            .build())
                    .build();

    private UserPrefs userPrefs = new UserPrefs();
    private CommandHistory commandHistory = new CommandHistory();

    /**
     * Returns a Model with an empty
     * By default, the set of appointments field is created but is empty
     */
    public Model build() {
        return new ModelManager(addressBook, userPrefs, commandHistory);
    }
}
