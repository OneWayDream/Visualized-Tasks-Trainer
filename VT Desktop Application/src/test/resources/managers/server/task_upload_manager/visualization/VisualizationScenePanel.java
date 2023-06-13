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
        inInitialStateCommand = "initialStyle";
        initialStepDelay = 1000;
        atSceneStartCommand = "initialStyle";
        atStartStepDelay = 1000;
        atSceneEndCommand = "thirdAnimation";
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
        panel.setBackground(Color.CYAN);
    }

    public void secondAnimation(){
        panel.setBackground(Color.GREEN);
    }

    public void thirdAnimation(){
        panel.setBackground(Color.RED);
    }

}
