package ru.itis.graduationwork.application.managers.project;

import ru.itis.graduationwork.application.entities.VisualizationAction;

import java.util.ArrayList;
import java.util.List;

public class VisualizationActionsManager {

    private static final List<VisualizationAction> actions = new ArrayList<>();
    private static int currentAction = 0;

    public static void addAction(VisualizationAction visualizationAction){
        actions.add(visualizationAction);
    }

    public static VisualizationAction onStart(){
        currentAction = 0;
        return actions.get(currentAction);
    }

    public static VisualizationAction onFinish(){
        currentAction = actions.size() - 1;
        return actions.get(currentAction);
    }

    public static VisualizationAction getNext(){
        VisualizationAction action = null;
        if (hasNext()){
            currentAction+=1;
            action = actions.get(currentAction);
        }
        return action;
    }

    public static VisualizationAction getCurrent(){
        return actions.get(currentAction);
    }

    public static VisualizationAction getPrevious(){
        VisualizationAction action = null;
        if (hasPrevious()){
            currentAction-=1;
            action = actions.get(currentAction);
        }
        return action;
    }

    public static boolean hasNext(){
        return currentAction + 1 < actions.size();
    }

    public static boolean hasPrevious(){
        return currentAction > 0;
    }

    public static void clear(){
        actions.clear();
        currentAction = 0;
    }

    public static boolean hasActions() {
        return !actions.isEmpty();
    }
}
