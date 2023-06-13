package ru.itis.visualtasks.desktopapp.application.managers.project.visualization;

import lombok.Getter;
import lombok.Setter;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationAction;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.VisualizationControlButtonsManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.VisualizationControlButtonsStatesManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.components.VisualizationPanel;
import ru.itis.visualtasks.desktopapp.exceptions.project.VisualizationMethodInvocationException;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UndefinedVisualizationMethodException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VisualizationSceneController {

    @Setter
    private static VisualizationPanel visualizationPanel;

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    @Getter
    private static boolean isPaused = false;

    public static void updateVisualizationScene(){
        visualizationPanel.updateContent();
    }


    public static void onStart(){
        if (VisualizationActionsManager.hasAnyActions()){
            handleNextVisualizationAction(VisualizationActionsManager.onStart());
            notifyIfTheFirstAction();
            notifyIfNotTheLastActionAnymore();
        }
    }

    public static void onFinish(){
        handleNextVisualizationAction(VisualizationActionsManager.onFinish());
        notifyIfTheLastAction();
        notifyIfNotTheFirstActionAnymore();
    }

    public static void nextStep(){
        handleNextVisualizationAction(VisualizationActionsManager.getNext());
        notifyIfNotTheFirstActionAnymore();
        notifyIfTheLastAction();
    }

    public static void previousStep(){
        handlePreviousVisualizationAction();
        notifyIfTheFirstAction();
        notifyIfNotTheLastActionAnymore();
    }

    public static void play(){
        isPaused = false;
        VisualizationControlButtonsManager.notifyAboutScenePlayingStart();
        handlePlayVisualizationAction();
    }

    public static void pause(){
        isPaused = true;
        executorService.shutdown();
        executorService = Executors.newScheduledThreadPool(1);
        notifyAboutPlayingSceneEnd();
    }

    private static void notifyIfTheFirstAction(){
        if (checkIfTheFirstAction()){
            VisualizationControlButtonsManager.notifyAboutTheFirstVisualizationAction();
        }
    }

    private static void notifyIfNotTheFirstActionAnymore(){
        if (checkIfNoMoreActionsBehindState() && checkIfNotTheFirstAction()){
            VisualizationControlButtonsManager.notifyAboutNotTheFirstVisualizationAction();
        }
    }

    private static boolean checkIfTheFirstAction(){
        return VisualizationActionsManager.getCurrentActionNumber() == 0;
    }

    private static boolean checkIfNoMoreActionsBehindState(){
        return !VisualizationControlButtonsStatesManager.isAnyPreviousActions();
    }

    private static boolean checkIfNotTheFirstAction(){
        return VisualizationActionsManager.getCurrentActionNumber() > 0;
    }

    private static void notifyIfTheLastAction(){
        if (checkIfTheLastAction()){
            VisualizationControlButtonsManager.notifyAboutTheLastVisualizationAction();
        }
    }

    private static boolean checkIfTheLastAction(){
        return VisualizationActionsManager.getCurrentActionNumber()
                == VisualizationActionsManager.getActionsAmount() - 1;
    }

    private static void notifyIfNotTheLastActionAnymore(){
        if (checkIfNoMoreNextActionsState() && checkIfNotTheLastAction()){
            VisualizationControlButtonsManager.notifyAboutNotTheLastVisualizationAction();
        }
    }

    private static boolean checkIfNoMoreNextActionsState(){
        return !VisualizationControlButtonsStatesManager.isAnyNextActions();
    }

    private static boolean checkIfNotTheLastAction(){
        return VisualizationActionsManager.getCurrentActionNumber() < VisualizationActionsManager.getActionsAmount() - 1;
    }

    private static void handleNextVisualizationAction(VisualizationAction visualizationAction){
        executorService.schedule(() -> {
                    try{
                        VisualizationActionsExecutor.executeCommand(visualizationAction.getNextStepCommand());
                    } catch (VisualizationMethodInvocationException | UndefinedVisualizationMethodException exception){
                        exception.handle();
                    }
                },
                VisualizationActionsManager.getCurrent().getStepDelay(), TimeUnit.MILLISECONDS);
    }

    private static void handlePlayVisualizationAction(){
        executorService.schedule(() -> {
            if (!VisualizationSceneController.isPaused()){
                try{
                    VisualizationAction visualizationAction = VisualizationActionsManager.getNext();
                    VisualizationActionsExecutor.executeCommand(visualizationAction.getNextStepCommand());
                    if (VisualizationActionsManager.hasNext()){
                        handlePlayVisualizationAction();
                    } else {
                        handleVisualizationEndAction();
                    }
                } catch (VisualizationMethodInvocationException | UndefinedVisualizationMethodException exception){
                    exception.handle();
                }
            }},
                VisualizationActionsManager.peekNext().getStepDelay(), TimeUnit.MILLISECONDS);
    }

    public static void handlePreviousVisualizationAction(){
        String commandAction = VisualizationActionsManager.getCurrent().getPreviousStepCommand();
        if (commandAction == null){
            commandAction = VisualizationActionsManager.getPrevious().getNextStepCommand();
        } else {
            VisualizationActionsManager.getPrevious();
        }
        String finalCommandAction = commandAction;
        executorService.schedule(() -> {
                    try{
                        VisualizationActionsExecutor.executeCommand(finalCommandAction);
                    } catch (VisualizationMethodInvocationException | UndefinedVisualizationMethodException exception){
                        exception.handle();
                    }
                },
                VisualizationActionsManager.getCurrent().getStepDelay(), TimeUnit.MILLISECONDS);
    }

    private static void handleVisualizationEndAction() {
        executorService.schedule(VisualizationSceneController::notifyAboutPlayingSceneEnd,
                0, TimeUnit.MILLISECONDS);
    }

    private static void notifyAboutPlayingSceneEnd(){
        VisualizationControlButtonsManager.notifyAboutScenePlayingFinish();
        VisualizationSceneController.notifyIfNotTheFirstActionAnymore();
        VisualizationSceneController.notifyIfTheLastAction();
    }

}
