package wrappers;

import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.registration.java.JavaVisualizationActionRegistrationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.WrappedClass;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationArgumentsStorage;
import wrappers.*;
import java.util.concurrent.CountDownLatch;

@WrappedClass(className = "CountDownLatch")
public class CountDownLatchWrapper {

    private CountDownLatch countDownLatch;

    public CountDownLatchWrapper(long count){
        countDownLatch  = new CountDownLatch(5);
    }

    public void countDown(){
        if (countDownLatch.getCount() == 5 ){
            JavaVisualizationActionRegistrationManager.registerAction("registerFirstRaceParticipant",
                    "initialStyle", 0);
            VisualizationArgumentsStorage.put("previous-action", "registerFirstRaceParticipant");
        } else if (countDownLatch.getCount() == 4){
            JavaVisualizationActionRegistrationManager.registerAction("registerSecondRaceParticipant",
                    "registerFirstRaceParticipant", 0);
            VisualizationArgumentsStorage.put("previous-action", "registerSecondRaceParticipant");
        } else if (countDownLatch.getCount() == 3){
            JavaVisualizationActionRegistrationManager.registerAction("showReadyRaceState",
                    "registerSecondRaceParticipant", 0);
            VisualizationArgumentsStorage.put("previous-action", "showReadyRaceState");
        } else if (countDownLatch.getCount() == 2){
            JavaVisualizationActionRegistrationManager.registerAction("showSteadyRaceState",
                    "showReadyRaceState", 0);
            VisualizationArgumentsStorage.put("previous-action", "showSteadyRaceState");
        } else if (countDownLatch.getCount() == 1){
            JavaVisualizationActionRegistrationManager.registerAction("showRacingState",
                    "showSteadyRaceState", 0);
            VisualizationArgumentsStorage.put("previous-action", "showRacingState");
        }
        countDownLatch.countDown();
    }

    public void await() throws InterruptedException{
        countDownLatch.await();
    }

}
