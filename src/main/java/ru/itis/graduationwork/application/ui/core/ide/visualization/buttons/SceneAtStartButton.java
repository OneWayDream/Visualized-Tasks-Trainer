package ru.itis.graduationwork.application.ui.core.ide.visualization.buttons;

import ru.itis.graduationwork.application.managers.project.visualization.buttons.VisualizationControlButtonsManager;
import ru.itis.graduationwork.application.managers.project.visualization.VisualizationSceneController;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;

import java.awt.event.ActionEvent;

public class SceneAtStartButton extends VisualizationButton {

    public SceneAtStartButton(){
        super();
        visualizationButtonType = VisualizationButtonType.AT_START;
        buttonIcon = Image.AT_START;
        createButton();
    }

    @Override
    protected void setEnabledTooltipText() {
        setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.scene-at-start.tooltip-text"));
    }

    @Override
    protected void registerInControlButtonsManager() {
        VisualizationControlButtonsManager.setSceneAtStartButton(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VisualizationSceneController.onStart();
    }

}
