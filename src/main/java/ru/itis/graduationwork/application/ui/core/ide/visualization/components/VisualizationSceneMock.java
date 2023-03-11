package ru.itis.graduationwork.application.ui.core.ide.visualization.components;

import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.ui.core.templates.Panel;

public class VisualizationSceneMock extends Panel {

    public VisualizationSceneMock(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setBackground(ColorsManager.getPanelBackgroundColor());
    }

    @Override
    protected void addComponents() {

    }

}
