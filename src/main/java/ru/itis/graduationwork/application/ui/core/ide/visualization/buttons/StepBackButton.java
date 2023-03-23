package ru.itis.graduationwork.application.ui.core.ide.visualization.buttons;

import ru.itis.graduationwork.application.managers.project.visualization.buttons.VisualizationControlButtonsManager;
import ru.itis.graduationwork.application.managers.project.visualization.VisualizationSceneController;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;

import java.awt.event.ActionEvent;

public class StepBackButton extends VisualizationButton {

    public StepBackButton(){
        super();
        visualizationButtonType = VisualizationButtonType.PREVIOUS_STEP;
        buttonIcon = Image.PREVIOUS;
        createButton();
    }

    @Override
    protected void setEnabledTooltipText() {
        setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.step-back.tooltip-text"));
    }

    @Override
    protected void registerInControlButtonsManager() {
        VisualizationControlButtonsManager.setStepBackButton(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VisualizationSceneController.previousStep();
    }

}
