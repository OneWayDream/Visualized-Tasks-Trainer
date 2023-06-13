import wrappers.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.SolutionScheme;

/*
    Students will use this file to solve your task. Write the necessary imports,
add comments and call some functions in the 'run' method
(we will hide it from the students during the solution).

*/
public class Solution extends SolutionScheme {

    @Override
    public void execute() {
        Wrapper wrapper = new Wrapper();
        wrapper.makeFirstAction();
        wrapper.makeSecondAction();
        wrapper.makeThirdAction();
    }

}
