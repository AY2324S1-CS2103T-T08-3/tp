package seedu.address.testutil;

import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BENSON;
import static seedu.address.testutil.TypicalEmployees.DANIEL;
import static seedu.address.testutil.TypicalEmployees.FIONA;
import static seedu.address.testutil.TypicalEmployees.GEORGE;
import static seedu.address.testutil.TypicalTasks.ALPHA_TASK;
import static seedu.address.testutil.TypicalTasks.BETA_TASK;
import static seedu.address.testutil.TypicalTasks.CHARLIE_TASK;
import static seedu.address.testutil.TypicalTasks.DELTA_TASK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskHub;
import seedu.address.model.project.Project;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {
    public static Project ALPHA_FACTORY() {
        return new ProjectBuilder().withTasks(ALPHA_TASK).withEmployees(ALICE).build();
    }
    public static Project BETA_FACTORY() {
        return new ProjectBuilder().withName("Beta").withEmployees(BENSON).withTasks(ALPHA_TASK)
                .withDeadline("10-12-2022").withCompletionStatus(false).build();
    }
    public static Project DELTA_FACTORY() {
        return  new ProjectBuilder().withName("Delta").withEmployees(DANIEL, FIONA)
                .withTasks(ALPHA_TASK, BETA_TASK)
                .withDeadline("24-11-2024").withCompletionStatus(true).build();

    }
    //Manually added
    public static final Project GAMMA_FACTORY() {
        return new ProjectBuilder().withName("Gamma").withEmployees(GEORGE)
                .withTasks(ALPHA_TASK, BETA_TASK, CHARLIE_TASK)
                .withDeadline("08-07-2023").build();
    }

    private static Project HIGH_PRIORITY_PROJECT_FACTORY() {
        return new  ProjectBuilder()
                .withName("High Priority Project")
                .withTasks(ALPHA_TASK, BETA_TASK, CHARLIE_TASK)
                .withPriority("high")
                .build();
    }
    private static Project LOW_PRIORITY_PROJECT_FACTORY() {
        return  new ProjectBuilder()
                .withName("Low Priority Project")
                .withTasks(ALPHA_TASK, BETA_TASK, CHARLIE_TASK)
                .withPriority("low")
                .build();
    }

    private static Project ASSIGNED_TASKS_PROJECT_FACTORY() {
        return new ProjectBuilder()
                .withName("Project with assigned task")
                .withEmployees(ALICE)
                .withTasks(ALPHA_TASK, DELTA_TASK)
                .build();
    }

    private TypicalProjects() {} // prevents instantiation

    /**
     * Returns an {@code TaskHub} with all the typical projects.
     */
    public static TaskHub getTypicalTaskHub() {
        return TypicalEmployees.getTypicalTaskHub();
    }

    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(ALPHA_FACTORY(), BETA_FACTORY(), DELTA_FACTORY(), GAMMA_FACTORY(),
                HIGH_PRIORITY_PROJECT_FACTORY(), LOW_PRIORITY_PROJECT_FACTORY(),
                ASSIGNED_TASKS_PROJECT_FACTORY()));
    }


}
