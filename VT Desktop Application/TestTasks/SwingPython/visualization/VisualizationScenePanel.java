package visualization;

import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.VisualizationSceneSwingPanelScheme;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.ButtonsColumnPanel;

import javax.swing.*;
import java.awt.*;

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
        inInitialStateCommand = "initialStyle";
        initialStepDelay = 1000;
        atSceneStartCommand = "initialStyle";
        atStartStepDelay = 1000;
        atSceneEndCommand = "firstAnimation";
        atEndStepDelay = 1000;
    }

    @Override
    protected void setPanelStyle() {
        panel.setBackground(Color.WHITE);
    }

    @Override
    protected void addComponents() {

    }

    public void initialStyle(){
        panel.setBackground(Color.WHITE);
    }

    public void firstAnimation(){
        panel.setBackground(Color.BLUE);
    }

    public void secondAnimation(){
        panel.setBackground(Color.GREEN);
    }

    public void thirdAnimation(){
        panel.setBackground(Color.RED);
    }

}
