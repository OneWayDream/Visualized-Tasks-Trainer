import ru.itis.visualtasks.desktopapp.application.compilers.java.JavaSolutionsFilesCompilerTests;
import ru.itis.visualtasks.desktopapp.application.entities.project.SolutionScheme;

public class TestSolution extends SolutionScheme {

    @Override
    public void execute() {
        JavaSolutionsFilesCompilerTests.TEST_SOLUTION_EXECUTION_FILE_CHECK = true;
    }

}