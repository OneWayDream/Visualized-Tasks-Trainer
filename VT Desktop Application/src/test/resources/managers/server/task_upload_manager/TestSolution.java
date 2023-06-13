import wrappers.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.SolutionScheme;

/*
    You can use this file to test your task. Write the necessary imports,
add comments and call some functions in the 'run' method.

*/
public class TestSolution extends SolutionScheme{

    @Override
    public void execute() {
        WrappedClass wrappedClass = new WrappedClass();
        wrappedClass.makeFirstAction();
        wrappedClass.makeSecondAction();
        wrappedClass.makeThirdAction();
    }

}
