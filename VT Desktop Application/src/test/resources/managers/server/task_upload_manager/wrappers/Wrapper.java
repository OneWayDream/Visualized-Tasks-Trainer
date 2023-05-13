package wrappers;

import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.registration.java.JavaVisualizationActionRegistrationManager;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationAction;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.WrappedClass;
import wrappers.*;

@WrappedClass(className = "WrappedClass")
public class Wrapper {
    
    public void makeFirstAction(){
        JavaVisualizationActionRegistrationManager.registerAction("firstAnimation", "initialStyle", 1000);
    }

    public void makeSecondAction(){
        JavaVisualizationActionRegistrationManager.registerAction("secondAnimation", "firstAnimation", 1000);
    }

    public void makeThirdAction(){
        JavaVisualizationActionRegistrationManager.registerAction("thirdAnimation", "secondAnimation", 1000);
    }

}
