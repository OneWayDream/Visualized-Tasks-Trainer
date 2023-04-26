package ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons;

import lombok.Setter;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationActionsManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.chain.VisualizationControlButtonsDisableReasonManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.buttons.*;
import ru.itis.visualtasks.desktopapp.exceptions.project.NoDisableReasonException;

public class VisualizationControlButtonsManager {

    @Setter
    private static PauseVisualizationSceneButton pauseVisualizationSceneButton;
    @Setter
    private static PlayVisualizationSceneButton playVisualizationSceneButton;
    @Setter
    private static SceneAtStartButton sceneAtStartButton;
    @Setter
    private static SceneAtEndButton sceneAtEndButton;
    @Setter
    private static StepBackButton stepBackButton;
    @Setter
    private static StepForwardButton stepForwardButton;

    public static String getDisableReason(VisualizationButtonType button){
        try{
            return VisualizationControlButtonsDisableReasonManager.getDisableReason(button);
        } catch (NoDisableReasonException exception){
            exception.handle();
            return "";
        }
    }

    public static void notifyAboutScenePlayingStart(){
        VisualizationControlButtonsStatesManager.notifyAboutScenePlaying();
        playVisualizationSceneButton.setEnabled(false);
        pauseVisualizationSceneButton.setEnabled(VisualizationControlButtonsStatesManager.isPlayPauseButtonsEnabled());
        sceneAtEndButton.setEnabled(false);
        sceneAtStartButton.setEnabled(false);
        stepBackButton.setEnabled(false);
        stepForwardButton.setEnabled(false);
    }

    public static void notifyAboutScenePlayingFinish(){
        VisualizationControlButtonsStatesManager.notifyAboutSceneStatic();
        playVisualizationSceneButton.setEnabled(VisualizationControlButtonsStatesManager.isPlayPauseButtonsEnabled());
        pauseVisualizationSceneButton.setEnabled(false);
        sceneAtEndButton.setEnabled(VisualizationControlButtonsStatesManager.isStartEndButtonsEnabled());
        sceneAtStartButton.setEnabled(VisualizationControlButtonsStatesManager.isStartEndButtonsEnabled());
        stepBackButton.setEnabled(VisualizationControlButtonsStatesManager.isForwardBackStepButtonsEnabled());
        stepForwardButton.setEnabled(VisualizationControlButtonsStatesManager.isForwardBackStepButtonsEnabled());
    }

    public static void notifyAboutFileExecution(){
        VisualizationControlButtonsStatesManager.notifyAboutFileExecution();
        if (checkIfActionsIsEmpty()){
            VisualizationControlButtonsStatesManager.notifyAboutEmptyActions();
            disableAllButtons();
        } else {
            playVisualizationSceneButton.setEnabled(VisualizationControlButtonsStatesManager.isPlayPauseButtonsEnabled());
            pauseVisualizationSceneButton.setEnabled(false);
            sceneAtEndButton.setEnabled(VisualizationControlButtonsStatesManager.isStartEndButtonsEnabled());
            sceneAtStartButton.setEnabled(false);
            stepBackButton.setEnabled(false);
            stepForwardButton.setEnabled(VisualizationControlButtonsStatesManager.isForwardBackStepButtonsEnabled());
        }
    }

    private static boolean checkIfActionsIsEmpty(){
        return !VisualizationActionsManager.hasAnyActions();
    }

    private static void disableAllButtons(){
        playVisualizationSceneButton.setEnabled(false);
        pauseVisualizationSceneButton.setEnabled(false);
        sceneAtEndButton.setEnabled(false);
        sceneAtStartButton.setEnabled(false);
        stepBackButton.setEnabled(false);
        stepForwardButton.setEnabled(false);
    }

    public static void notifyAboutTheFirstVisualizationAction(){
        VisualizationControlButtonsStatesManager.notifyAboutTheFirstVisualizationAction();
        sceneAtStartButton.setEnabled(false);
        stepBackButton.setEnabled(false);
    }

    public static void notifyAboutNotTheFirstVisualizationAction(){
        VisualizationControlButtonsStatesManager.notifyAboutNotTheFirstVisualizationAction();
        sceneAtStartButton.setEnabled(VisualizationControlButtonsStatesManager.isStartEndButtonsEnabled());
        stepBackButton.setEnabled(VisualizationControlButtonsStatesManager.isForwardBackStepButtonsEnabled());
    }

    public static void notifyAboutTheLastVisualizationAction(){
        VisualizationControlButtonsStatesManager.notifyAboutTheLastVisualizationAction();
        sceneAtEndButton.setEnabled(false);
        stepForwardButton.setEnabled(false);
        playVisualizationSceneButton.setEnabled(false);
    }

    public static void notifyAboutNotTheLastVisualizationAction(){
        VisualizationControlButtonsStatesManager.notifyAboutNotTheLastVisualizationAction();
        sceneAtEndButton.setEnabled(VisualizationControlButtonsStatesManager.isStartEndButtonsEnabled());
        stepForwardButton.setEnabled(VisualizationControlButtonsStatesManager.isForwardBackStepButtonsEnabled());
        playVisualizationSceneButton.setEnabled(VisualizationControlButtonsStatesManager.isPlayPauseButtonsEnabled());
    }

    public static void reset(){
        pauseVisualizationSceneButton = null;
        playVisualizationSceneButton = null;
        sceneAtStartButton = null;
        sceneAtEndButton = null;
        stepBackButton = null;
        stepForwardButton = null;
        VisualizationControlButtonsStatesManager.reset();
    }

}
