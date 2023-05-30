import wrappers.*;
import ru.itis.visualtasks.application.entities.project.SolutionScheme;

/*
    You can use this file to test your task. Write the necessary imports,
add comments and call some functions in the 'run' method.

*/
public class TestSolution extends SolutionScheme {

    @Override
    public void execute() {
        Wrapper wrapper = new Wrapper();
        wrapper.makeFirstAction();
        wrapper.makeSecondAction();
        wrapper.makeThirdAction();
    }

}