package ru.itis.graduationwork.application.ui.core.ide.visualization.buttons;

import ru.itis.graduationwork.application.managers.project.visualization.buttons.VisualizationControlButtonsManager;
import ru.itis.graduationwork.application.managers.project.visualization.VisualizationSceneController;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;

import java.awt.event.ActionEvent;

public class StepForwardButton extends VisualizationButton {

    public StepForwardButton(){
        super();
        visualizationButtonType = VisualizationButtonType.NEXT_STEP;
        buttonIcon = Image.NEXT;
        createButton();
    }

    @Override
    protected void setEnabledTooltipText() {
        setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.step-forward.tooltip-text"));
    }

    @Override
    protected void registerInControlButtonsManager() {
        VisualizationControlButtonsManager.setStepForwardButton(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VisualizationSceneController.nextStep();
    }

}
