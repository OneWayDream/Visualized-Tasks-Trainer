package ru.itis.graduationwork.application.ui.pages.develop.panels.visualization;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.ui.core.templates.Panel;

import javax.swing.*;
import java.awt.*;

public class VisualizationPanel extends Panel {

    public VisualizationPanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setBorder((BorderFactory.createMatteBorder(5, 5, 5, 5,
                ColorsManager.getBordersColor())));
        panel.setPreferredSize(new Dimension((int) (Application.getCurrentPageFrame().getWidth() * 0.3),
                0));
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());

    }

    @Override
    protected void addComponents() {
        addVisualizationScenePanel();
        addVisualizationControlPanel();
    }

    private void addVisualizationScenePanel() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridy = 0;
        constraint.gridx = 0;
        constraint.weightx = 1;
        constraint.weighty = 0.75;
        panel.add(new VisualizationScenePanel().getComponent(), constraint);
    }

    private void addVisualizationControlPanel() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridy = 1;
        constraint.gridx = 0;
        constraint.weightx = 1;
        constraint.weighty = 0.25;
        panel.add(new VisualizationControlPanel().getComponent(), constraint);
    }

}
