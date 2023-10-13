package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEmployeeAtIndex;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EMPLOYEE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Remark;
import seedu.address.testutil.EmployeeBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Employee firstEmployee = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        Employee editedEmployee = new EmployeeBuilder(firstEmployee).withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_EMPLOYEE,
                new Remark(editedEmployee.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedEmployee);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEmployee(firstEmployee, editedEmployee);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Employee firstEmployee = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        Employee editedEmployee = new EmployeeBuilder(firstEmployee).withRemark("").build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_EMPLOYEE,
                new Remark(editedEmployee.getRemark().toString()));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedEmployee);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEmployee(firstEmployee, editedEmployee);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showEmployeeAtIndex(model, INDEX_FIRST_EMPLOYEE);

        Employee firstEmployee = model.getFilteredEmployeeList()
                .get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        Employee editedEmployee = new EmployeeBuilder(model.getFilteredEmployeeList()
                .get(INDEX_FIRST_EMPLOYEE.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_EMPLOYEE,
                new Remark(editedEmployee.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedEmployee);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEmployee(firstEmployee, editedEmployee);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEmployeeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEmployeeList().size() + 1);
        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidEmployeeIndexFilteredList_failure() {
        showEmployeeAtIndex(model, INDEX_FIRST_EMPLOYEE);
        Index outOfBoundIndex = INDEX_SECOND_EMPLOYEE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getEmployeeList().size());

        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_EMPLOYEE,
                new Remark(VALID_REMARK_AMY));

        // same values -> returns true
        RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_EMPLOYEE,
                new Remark(VALID_REMARK_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_SECOND_EMPLOYEE,
                new Remark(VALID_REMARK_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_FIRST_EMPLOYEE,
                new Remark(VALID_REMARK_BOB))));
    }
}