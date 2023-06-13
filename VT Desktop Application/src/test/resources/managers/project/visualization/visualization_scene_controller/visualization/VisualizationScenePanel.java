package visualization;

import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.VisualizationSceneSwingPanelScheme;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.ButtonsColumnPanel;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationSceneControllerTests;

import javax.swing.*;
import java.awt.*;

public class VisualizationScenePanel extends VisualizationSceneSwingPanelScheme {

    public VisualizationScenePanel(JPanel panel){
        super(panel);
    }

    /*
    Use this method to configure the visualisation control buttons (you can disable some of them
if you don't have an implementation for these actions).
    */
    @Override
    protected void adjustControlButtons() {
        isStartEndButtonsEnabled = true;
        isForwardBackStepButtonsEnabled = true;
        isPlayPauseButtonsEnabled = true;
    }

    @Override
    protected void adjustEdgesCommands() {
        inInitialStateCommand = "initialStyle";
        initialStepDelay = 0;
        atSceneStartCommand = "onStartAnimation";
        atStartStepDelay = 0;
        atSceneEndCommand = "onFinishAnimation";
        atEndStepDelay = 0;
    }

    @Override
    protected void setPanelStyle() {

    }

    @Override
    protected void addComponents() {

    }

    public void initialStyle(){
        VisualizationSceneControllerTests.clearRegistry();
    }

    public void firstAnimation(){
        VisualizationSceneControllerTests.registryAction(1);
    }

    public void secondAnimation(){
        VisualizationSceneControllerTests.registryAction(2);
    }

    public void thirdAnimation(){
        VisualizationSceneControllerTests.registryAction(3);
    }

    public void onStartAnimation(){
        VisualizationSceneControllerTests.clearRegistry();
    }

    public void onFinishAnimation(){
        VisualizationSceneControllerTests.registryAction(1);
        VisualizationSceneControllerTests.registryAction(2);
        VisualizationSceneControllerTests.registryAction(3);
    }

}
