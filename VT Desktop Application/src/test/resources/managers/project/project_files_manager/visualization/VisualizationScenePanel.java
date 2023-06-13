package visualization;

import java.awt.*;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.VisualizationSceneSwingPanelScheme;
import javax.swing.*;

/*
    Use this class to customize your panel as a component (setting styles and adding internal components).   
    To access the JPanel component (we'll pass an instance of your CustomPanel to the constructor),  
    use the 'panel' variable.
*/
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
