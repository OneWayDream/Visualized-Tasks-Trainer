package ru.itis.graduationwork.application.ui.pages.develop.panels.visualization;

import ru.itis.graduationwork.application.ui.core.templates.Panel;

import java.awt.*;

public class VisualizationScenePanel extends Panel {

    public VisualizationScenePanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setBackground(Color.YELLOW);
    }

    @Override
    protected void addComponents() {

    }
}
