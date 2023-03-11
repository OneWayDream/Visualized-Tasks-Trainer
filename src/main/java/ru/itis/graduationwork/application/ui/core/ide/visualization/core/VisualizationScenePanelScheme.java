package ru.itis.graduationwork.application.ui.core.ide.visualization.core;

import lombok.Getter;
import ru.itis.graduationwork.application.ui.core.templates.Component;

import javax.swing.*;

public abstract class VisualizationScenePanelScheme implements Component {

    protected JPanel panel;
    @Getter
    protected boolean isStatic = true;

    public VisualizationScenePanelScheme(JPanel jpanel){
        panel = jpanel;
        setPanelStyle();
        addComponents();
    }

    protected abstract void setPanelStyle();

    protected abstract void addComponents();

    public abstract void executeCommand(String command);

    @Override
    public JComponent getComponent() {
        return panel;
    }

}
