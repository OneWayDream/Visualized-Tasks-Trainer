package wrappers;

import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.WrappedClass;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.registration.java.JavaVisualizationActionRegistrationManager;
import wrappers.*;

@WrappedClass(className = "WrappedClass")
public class Wrapper {

    public void makeFirstAction(){
        JavaVisualizationActionRegistrationManager.registerAction("firstAnimation", "initialStyle", 200);
    }

    public void makeSecondAction(){
        JavaVisualizationActionRegistrationManager.registerAction("secondAnimation", "firstAnimation", 200);
    }

    public void makeThirdAction(){
        JavaVisualizationActionRegistrationManager.registerAction("thirdAnimation", "secondAnimation", 200);
    }

}