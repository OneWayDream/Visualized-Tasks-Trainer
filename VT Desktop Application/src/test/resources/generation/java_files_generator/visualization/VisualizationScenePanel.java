package visualization;

import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.VisualizationSceneJavaFxPanelScheme;
import javafx.embed.swing.JFXPanel;
import javafx.scene.*;

/*
    Use this class to customize your panel as a component (setting styles and adding internal components).   
    To access the JFXPanel component (we'll pass an instance of your CustomPanel to the constructor),  
    override the 'configureScene' method using 'scene' variable.
*/
public class VisualizationScenePanel extends VisualizationSceneJavaFxPanelScheme {

    public VisualizationScenePanel(JFXPanel panel){
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
    protected void configureScene() {
        scene = null;
    }

    @Override
    protected void addComponents() {

    }

}
