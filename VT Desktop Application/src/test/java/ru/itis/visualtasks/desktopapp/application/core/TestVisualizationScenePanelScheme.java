package ru.itis.visualtasks.desktopapp.application.core;

import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationActionsExecutorTests;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.VisualizationScenePanelScheme;

import javax.swing.*;

public class TestVisualizationScenePanelScheme extends VisualizationScenePanelScheme {

    @Override
    protected void adjustControlButtons() {

    }

    @Override
    protected void adjustEdgesCommands() {

    }

    @Override
    protected void addComponents() {

    }

    @Override
    public JComponent getComponent() {
        return null;
    }

    public void makeAction(){
        VisualizationActionsExecutorTests.ACTION_CHECK_FLAG = true;
    }

}
