package ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;

public abstract class VisualizationSceneJavaFxPanelScheme extends VisualizationScenePanelScheme {

    protected final JFXPanel panel;
    protected Scene scene;

    public VisualizationSceneJavaFxPanelScheme(JFXPanel jfxPanel){
        super();
        this.panel = jfxPanel;
        adjustControlButtons();
        adjustEdgesCommands();
        configureScene();
        addComponents();
        panel.setScene(scene);
    }

    protected abstract void configureScene();

    @Override
    public JComponent getComponent() {
        return panel;
    }

}
