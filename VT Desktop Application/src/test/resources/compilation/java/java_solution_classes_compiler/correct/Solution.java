import ru.itis.visualtasks.desktopapp.application.compilers.java.JavaSolutionsFilesCompilerTests;
import ru.itis.visualtasks.desktopapp.application.entities.project.SolutionScheme;

public class Solution extends SolutionScheme {

    @Override
    public void execute() {
        JavaSolutionsFilesCompilerTests.SOLUTION_EXECUTION_FILE_CHECK = true;
    }

}