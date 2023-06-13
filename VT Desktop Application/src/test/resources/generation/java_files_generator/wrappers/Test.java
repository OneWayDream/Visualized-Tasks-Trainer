package wrappers;

import ru.itis.graduationwork.desktopapp.application.managers.project.visualization.registration.java.JavaVisualizationActionRegistrationManager;
import wrappers.*;
import ru.itis.graduationwork.desktopapp.application.ui.core.ide.visualization.core.WrappedClass;

/*
    Define in this annotation the name of the class you are wrapping. This way the student  
    can use the normal language syntax, but your wrapper classes will be called. If the   
    class does not perform the wrapper functionality, simply delete the annotation.
*/
/*
    To bind your code and the visualization scene, register the visualization action in   
    the code when it is to be executed. Use this method to do it:
    VisualizationActionsManager.addAction(new VisualizationAction(
            "toNextStepActionFunctionName", "returnStepActionFunctionName", 1000 (time delay in ms)
    ));
*/
@WrappedClass(classname = "*your wrapped class name*")
public class Test {



}