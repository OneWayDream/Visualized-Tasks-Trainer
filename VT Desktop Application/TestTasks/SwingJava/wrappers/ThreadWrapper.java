package wrappers;

import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.registration.java.JavaVisualizationActionRegistrationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.WrappedClass;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationArgumentsStorage;
import wrappers.*;

@WrappedClass(className = "Thread")
public class ThreadWrapper {

    public static void sleep(long millis) throws InterruptedException {
        JavaVisualizationActionRegistrationManager.registerAction("waitAction",
                (String) VisualizationArgumentsStorage.get("previous-action"), millis);
        Thread.sleep(millis);
        VisualizationArgumentsStorage.put("previous-action", "waitAction");
    }

}