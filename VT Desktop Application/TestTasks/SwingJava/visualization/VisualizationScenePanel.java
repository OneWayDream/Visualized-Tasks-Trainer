package visualization;

import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.VisualizationSceneSwingPanelScheme;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.ButtonsColumnPanel;

import javax.swing.*;
import java.awt.*;
import visualization.panels.*;
import visualization.managers.*;

public class VisualizationScenePanel extends VisualizationSceneSwingPanelScheme {

    private boolean firstCarRegistredState;
    private boolean secondCarRegistredState;
    private RaceState raceState;


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
        atSceneStartCommand = "initialStyle";
        atStartStepDelay = 0;
        atSceneEndCommand = null;
        atEndStepDelay = 0;
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new BorderLayout());
        resetStates();
    }

    @Override
    protected void addComponents() {
        VisualizationSettings visualizationSettings = getVisualizationSettings();
        RaceInfoHeader raceInfoHeader = new RaceInfoHeader(visualizationSettings);
        panel.add(raceInfoHeader.getComponent(), BorderLayout.NORTH);
        panel.add(new RacePanel(visualizationSettings, raceInfoHeader).getComponent(), BorderLayout.CENTER);
    }

    private VisualizationSettings getVisualizationSettings(){
        VisualizationSettings visualizationSettings = new VisualizationSettings();
        if (firstCarRegistredState){
            visualizationSettings.setFirstCarRegistred(RaceManager.isFirstCarRegister());
            visualizationSettings.setFirstCarSpeed(RaceManager.getFirstCarSpeed());
        }
        if (secondCarRegistredState){
            visualizationSettings.setSecondCarRegistred(RaceManager.isSecondCarRegister());
            visualizationSettings.setSecondCarSpeed(RaceManager.getSecondCarSpeed());
        }
        visualizationSettings.setRaceState(raceState);
        return visualizationSettings;
    }

    public void initialStyle(){
        resetStates();
        updatePanelContent();
    }

    private void resetStates(){
        firstCarRegistredState = false;
        secondCarRegistredState = false;
        raceState = RaceState.WAITING;
    }

    private void updatePanelContent(){
        panel.removeAll();
        addComponents();
        panel.validate();
        panel.repaint();
    }

    public void registerFirstRaceParticipant(){
        raceState = RaceState.WAITING;
        firstCarRegistredState = true;
        secondCarRegistredState = false;
        updatePanelContent();
    }

    public void registerSecondRaceParticipant(){
        raceState = RaceState.WAITING;
        secondCarRegistredState = true;
        updatePanelContent();
    }

    public void showReadyRaceState(){
        raceState = RaceState.READY;
        updatePanelContent();
    }

    public void showSteadyRaceState(){
        raceState = RaceState.STEADY;
        updatePanelContent();
    }

    public void showRacingState(){
        raceState = RaceState.GO;
        updatePanelContent();
    }

    public void waitAction(){

    }

}
