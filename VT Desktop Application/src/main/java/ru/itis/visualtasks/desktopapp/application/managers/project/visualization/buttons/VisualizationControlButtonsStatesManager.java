package ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons;

import lombok.Setter;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationActionsManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationSceneController;

public class VisualizationControlButtonsStatesManager {

    private static boolean isScenePlayingFlag = false;
    private static boolean isSolutionFileExecutedFlag = false;
    private static boolean isActionsEmptyFlag = false;
    private static boolean isAnyPreviousActionsFlag = false;
    private static boolean isAnyNextActionsFlag = false;

    @Setter
    private static boolean isStartEndButtonsEnabledFlag = true;
    @Setter
    private static boolean isForwardBackStepButtonsEnabledFlag = true;
    @Setter
    private static boolean isPlayPauseButtonsEnabledFlag = true;

    public static boolean isSolutionFileExecuted(){
        return isSolutionFileExecutedFlag;
    }

    public static boolean isScenePlaying(){
        return isScenePlayingFlag;
    }

    public static boolean isAnyPreviousActions(){
        return isAnyPreviousActionsFlag;
    }

    public static boolean isAnyNextActions(){
        return isAnyNextActionsFlag;
    }
    public static boolean isAnyActions(){
        return !isActionsEmptyFlag;
    }

    public static boolean isStartEndButtonsEnabled(){
        return isStartEndButtonsEnabledFlag;
    }

    public static boolean isForwardBackStepButtonsEnabled(){
        return isForwardBackStepButtonsEnabledFlag;
    }

    public static boolean isPlayPauseButtonsEnabled(){
        return isPlayPauseButtonsEnabledFlag;
    }

    public static void notifyAboutFileExecution(){
        isSolutionFileExecutedFlag = true;
        isAnyNextActionsFlag = (VisualizationActionsManager.getActionsAmount() > 1);
        VisualizationSceneController.updateVisualizationScene();
    }

    public static void notifyAboutEmptyActions(){
        isActionsEmptyFlag = true;
    }

    public static void notifyAboutTheFirstVisualizationAction(){
        isAnyPreviousActionsFlag = false;
    }

    public static void notifyAboutNotTheFirstVisualizationAction(){
        isAnyPreviousActionsFlag = true;
    }

    public static void notifyAboutTheLastVisualizationAction(){
        isAnyNextActionsFlag = false;
    }

    public static void notifyAboutNotTheLastVisualizationAction(){
        isAnyNextActionsFlag = true;
    }

    public static void notifyAboutScenePlaying(){
        isScenePlayingFlag = true;
    }

    public static void notifyAboutSceneStatic(){
        isScenePlayingFlag = false;
    }

    public static void reset(){
        isSolutionFileExecutedFlag = false;
        isScenePlayingFlag = false;
        isAnyPreviousActionsFlag = false;
        isAnyNextActionsFlag = false;
    }

}
