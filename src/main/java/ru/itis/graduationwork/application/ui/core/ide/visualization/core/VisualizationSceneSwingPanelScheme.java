package ru.itis.graduationwork.application.ui.core.ide.visualization.core;

import javax.swing.*;

public abstract class VisualizationSceneSwingPanelScheme extends VisualizationScenePanelScheme {

    protected JPanel panel;

    public VisualizationSceneSwingPanelScheme(JPanel jPanel){
        super();
        this.panel = jPanel;
        adjustControlButtons();
        adjustEdgesCommands();
        setPanelStyle();
        addComponents();
    }

    protected abstract void setPanelStyle();

    @Override
    public JComponent getComponent() {
        return panel;
    }

}
