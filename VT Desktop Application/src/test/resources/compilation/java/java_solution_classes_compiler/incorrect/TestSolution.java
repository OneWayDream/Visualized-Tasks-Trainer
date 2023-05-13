import ru.itis.visualtasks.desktopapp.application.entities.project.SolutionScheme;

public class TestSolution extends SolutionScheme {

    @Override
    public void execute() {
        throw new IllegalArgumentException();
    }

}