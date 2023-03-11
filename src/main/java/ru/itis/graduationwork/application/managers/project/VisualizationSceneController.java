package ru.itis.graduationwork.application.managers.project;

import lombok.Setter;
import ru.itis.graduationwork.application.entities.VisualizationAction;
import ru.itis.graduationwork.application.ui.core.ide.visualization.core.VisualizationScenePanelScheme;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VisualizationSceneController {

    @Setter
    private static VisualizationScenePanelScheme visualizationScenePanel;
    private static long nextDelay = 0;
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public static void onStart(){
        if (VisualizationActionsManager.hasActions()){
            nextDelay = 0;
            handleVisualizationAction(VisualizationActionsManager.onStart());
        }
    }

    public static void onFinish(){
        nextDelay = 0;
        handleVisualizationAction(VisualizationActionsManager.onFinish());
    }

    public static void nextStep(){
        nextDelay = 0;
        handleVisualizationAction(VisualizationActionsManager.getNext());
    }

    public static void previousStep(){
        nextDelay = 0;
        handleVisualizationAction(VisualizationActionsManager.getPrevious());
    }

    public static void play(){
        while (VisualizationActionsManager.hasNext()){
            handlePlayVisualizationAction(VisualizationActionsManager.getNext());
        }
    }

    public static void pause(){
        executorService.shutdown();
        executorService = Executors.newScheduledThreadPool(1);
    }

    private static void handleVisualizationAction(VisualizationAction visualizationAction){
        executorService.schedule(() -> visualizationScenePanel.executeCommand(visualizationAction.getCommand()),
                nextDelay, TimeUnit.MILLISECONDS);
        nextDelay = visualizationAction.getTimeToNext();
    }

    private static void handlePlayVisualizationAction(VisualizationAction visualizationAction){
        executorService.schedule(() -> visualizationScenePanel.executeCommand(visualizationAction.getCommand()),
                nextDelay, TimeUnit.MILLISECONDS);
        nextDelay += visualizationAction.getTimeToNext();
    }


}
