package ru.itis.graduationwork.application.ui.core.ide.visualization.buttons;

import ru.itis.graduationwork.application.managers.content.IconsManager;
import ru.itis.graduationwork.application.managers.project.VisualizationSceneController;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.ide.IdeFramesIconsConstants;
import ru.itis.graduationwork.application.ui.core.templates.Button;

import java.awt.event.ActionEvent;

public class StepBackButton extends Button {

    public StepBackButton(){
        super();
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        disableDefaultButtonStyle();
        setIcon();
        setToolTipText();
    }

    private void setIcon(){
        button.setIcon(IconsManager.getImageIcon(Image.PREVIOUS,
                IdeFramesIconsConstants.VISUALIZATION_ICON_BUTTON_WIDTH,
                IdeFramesIconsConstants.VISUALIZATION_ICON_BUTTON_HEIGHT));
    }

    private void setToolTipText(){
        button.setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.step-back.tooltip-text"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VisualizationSceneController.previousStep();
    }

}
