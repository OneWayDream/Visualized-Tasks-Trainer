package ru.itis.graduationwork.application.ui.core.ide.visualization.components;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.settings.Mode;
import ru.itis.graduationwork.application.ui.core.ide.visualization.buttons.*;
import ru.itis.graduationwork.application.ui.core.templates.Panel;

import java.awt.*;

public class VisualizationControlPanel extends Panel {

    public VisualizationControlPanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
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
        if (Application.getMode() == Mode.DEVELOP){
            addUpdateSceneButton();
        }
    }

    private void addSceneToStartButton() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridy = 0;
        constraint.gridx = 0;
        constraint.insets = new Insets(0,0,0,5);
        panel.add(new SceneAtStartButton().getComponent(), constraint);
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
        panel.add(new SceneAtEndButton().getComponent(), constraint);
    }

    private void addUpdateSceneButton(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridy = 0;
        constraint.gridx = 6;
        constraint.insets = new Insets(0,5,0,5);
        panel.add(new UpdateVisualizationSceneButton().getComponent(), constraint);
    }

}
