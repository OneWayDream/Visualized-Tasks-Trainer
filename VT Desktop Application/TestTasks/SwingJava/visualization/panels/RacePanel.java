package visualization.panels;

import javax.swing.*;
import java.awt.*;
import visualization.managers.*;
import visualization.panels.*;

public class RacePanel {

    private JPanel panel;
    private VisualizationSettings visualizationSettings;
    private RaceInfoHeader raceInfoHeader;

    public RacePanel(VisualizationSettings visualizationSettings, RaceInfoHeader raceInfoHeader){
        panel = new JPanel();
        this.visualizationSettings = visualizationSettings;
        this.raceInfoHeader = raceInfoHeader;
        createPanel();
    }

    private void createPanel(){
        setPanelStyle();
        addComponents();
    }

    private void setPanelStyle(){
        panel.setBackground(Color.decode("#98FB98"));
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setLayout(new GridBagLayout());
    }

    private void addComponents(){
        addFirstRoad();
        addSecondRoad();
    }

    private void addFirstRoad(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.VERTICAL;
        constraint.gridy = 0;
        constraint.gridx = 0;
        constraint.weightx = 1;
        constraint.weighty = 1;
        constraint.insets = new Insets(0, 0, 0, -150);
        panel.add(new RoadPanel(1, visualizationSettings, raceInfoHeader).getComponent(), constraint);
    }

    private void addSecondRoad(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.VERTICAL;
        constraint.gridy = 0;
        constraint.gridx = 1;
        constraint.weightx = 1;
        constraint.weighty = 1;
        panel.add(new RoadPanel(2, visualizationSettings, raceInfoHeader).getComponent(), constraint);
    }

    public JComponent getComponent() {
        return panel;
    }

}