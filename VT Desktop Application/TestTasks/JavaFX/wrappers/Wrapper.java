package wrappers;

import ru.itis.visualtasks.application.managers.project.visualization.VisualizationActionsManager;
import ru.itis.visualtasks.application.entities.project.VisualizationAction;

public class Wrapper {
    
    public void makeFirstAction(){
        VisualizationActionsManager.addAction(new VisualizationAction(
                "firstAnimation", "initialStyle", 1000
        ));
    }

    public void makeSecondAction(){
        VisualizationActionsManager.addAction(new VisualizationAction(
                "secondAnimation", "firstAnimation", 1000
        ));
    }

    public void makeThirdAction(){
        VisualizationActionsManager.addAction(new VisualizationAction(
                "thirdAnimation", "secondAnimation", 1000
        ));
    }

}
