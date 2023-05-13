package visualization;

import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.VisualizationSceneSwingPanelScheme;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.ButtonsColumnPanel;

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
        inInitialStateCommand = null;
        initialStepDelay = 0;
        atSceneStartCommand = null;
        atStartStepDelay = 0;
        atSceneEndCommand = null;
        atEndStepDelay = 0;
    }

    @Override
    protected void setPanelStyle() {

    }

    @Override
    protected void addComponents() {

    }

}
