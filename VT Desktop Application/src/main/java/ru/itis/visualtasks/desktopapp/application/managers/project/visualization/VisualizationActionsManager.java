package ru.itis.visualtasks.desktopapp.application.managers.project.visualization;

import lombok.Getter;
import lombok.Setter;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationAction;

import java.util.ArrayList;
import java.util.List;

public class VisualizationActionsManager {

    private static final List<VisualizationAction> actions = new ArrayList<>();
    @Getter
    private static int currentActionNumber = 0;
    @Setter
    private static String inInitialStateCommand;
    @Setter
    private static long initialStepDelay;
    @Setter
    private static String atSceneStartCommand;
    @Setter
    private static long atStartStepDelay;
    @Setter
    private static String atSceneEndCommand;
    @Setter
    private static long atEndStepDelay;

    public static int getActionsAmount(){
        return actions.size();
    }

    public static void addAction(VisualizationAction visualizationAction){
        actions.add(visualizationAction);
    }

    public static VisualizationAction onStart(){
        currentActionNumber = 0;
        return (atSceneStartCommand == null) ? actions.get(currentActionNumber)
                : new VisualizationAction(atSceneStartCommand, null, atStartStepDelay);
    }

    public static VisualizationAction onFinish(){
        currentActionNumber = actions.size() - 1;
        return (atSceneEndCommand == null) ? actions.get(currentActionNumber)
                : new VisualizationAction(atSceneEndCommand, null, atEndStepDelay);
    }

    public static VisualizationAction getNext(){
        VisualizationAction action = null;
        if (hasNext()){
            currentActionNumber +=1;
            action = actions.get(currentActionNumber);
        }
        return action;
    }

    public static VisualizationAction peekNext(){
        VisualizationAction action = null;
        if (hasNext()){
            action = actions.get(currentActionNumber + 1);
        }
        return action;
    }


    public static VisualizationAction getCurrent(){
        return actions.get(currentActionNumber);
    }

    public static VisualizationAction getPrevious(){
        VisualizationAction action = null;
        if (hasPrevious()){
            currentActionNumber -=1;
            action = actions.get(currentActionNumber);
        }
        return action;
    }

    public static boolean hasNext(){
        return currentActionNumber + 1 < actions.size();
    }

    public static boolean hasPrevious(){
        return currentActionNumber > 0;
    }

    public static void clear(){
        actions.clear();
        if (inInitialStateCommand != null){
            actions.add(new VisualizationAction(inInitialStateCommand, null, initialStepDelay));
        }
        currentActionNumber = 0;
    }

    public static boolean hasAnyActions() {
        return !actions.isEmpty();
    }
}
