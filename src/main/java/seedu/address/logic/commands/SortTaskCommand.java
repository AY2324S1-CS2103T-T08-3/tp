package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.project.Project;


/**
 * Sorts tasks in each project according to completion and deadline.
 * Incomplete tasks will appear before complete tasks and tasks will be arranged in ascending deadline order.
 */
public class SortTaskCommand extends Command {

    public static final String COMMAND_WORD = "sortT";

    public static final String MESSAGE_SUCCESS = "Sorted all tasks according to completion status and deadline";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Project> lastShownProjectList = model.getFilteredProjectList();
        for (Project project : lastShownProjectList) {
            project.sortTasksByDeadlineAndCompletion();
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}