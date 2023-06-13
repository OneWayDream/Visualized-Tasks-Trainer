import ru.itis.visualtasks.desktopapp.application.managers.project.JavaProjectTaskFilesManagerTests;
import ru.itis.visualtasks.desktopapp.application.entities.project.SolutionScheme;

public class TestSolution extends SolutionScheme {

    @Override
    public void execute() {
        JavaProjectTaskFilesManagerTests.TEST_SOLUTION_EXECUTION_FILE_CHECK = true;
    }

}