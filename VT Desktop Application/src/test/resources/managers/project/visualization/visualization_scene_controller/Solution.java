import wrappers.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.SolutionScheme;

public class Solution extends SolutionScheme {

    @Override
    public void execute() {
        WrappedClass wrappedClass = new WrappedClass();
        wrappedClass.makeFirstAction();
        wrappedClass.makeSecondAction();
        wrappedClass.makeThirdAction();
    }

}