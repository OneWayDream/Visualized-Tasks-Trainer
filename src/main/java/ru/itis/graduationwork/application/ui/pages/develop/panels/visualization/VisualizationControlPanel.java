package ru.itis.graduationwork.application.ui.pages.develop.panels.visualization;

import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.ui.core.templates.Panel;
import ru.itis.graduationwork.application.ui.pages.develop.buttons.visualization.*;

import java.awt.*;

public class VisualizationControlPanel extends Panel {

    public VisualizationControlPanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setBackground(ColorsManager.getPanelBackgroundColor());
        panel.setLayout(new GridBagLayout());
    }

    @Override
    protected void addComponents() {
        addSceneToStartButton();
        addStepBackButton();
        addPauseVisualizationSceneButton();
        addPlayVisualizationSceneButton();
        addStepForwardButton();
        addSceneToEndButton();
    }

    private void addSceneToStartButton() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridy = 0;
        constraint.gridx = 0;
        constraint.insets = new Insets(0,0,0,5);
        panel.add(new SceneToStartButton().getComponent(), constraint);
    }

    private void addStepBackButton() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridy = 0;
        constraint.gridx = 1;
        constraint.insets = new Insets(0,5,0,5);
        panel.add(new StepBackButton().getComponent(), constraint);
    }

    private void addPauseVisualizationSceneButton() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridy = 0;
        constraint.gridx = 2;
        constraint.insets = new Insets(0,5,0,5);
        panel.add(new PauseVisualizationSceneButton().getComponent(), constraint);
    }

    private void addPlayVisualizationSceneButton() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridy = 0;
        constraint.gridx = 3;
        constraint.insets = new Insets(0,5,0,5);
        panel.add(new PlayVisualizationSceneButton().getComponent(), constraint);
    }

    private void addStepForwardButton() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridy = 0;
        constraint.gridx = 4;
        constraint.insets = new Insets(0,5,0,5);
        panel.add(new StepForwardButton().getComponent(), constraint);
    }

    private void addSceneToEndButton() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridy = 0;
        constraint.gridx = 5;
        constraint.insets = new Insets(0,5,0,0);
        panel.add(new SceneToEndButton().getComponent(), constraint);
    }

}
