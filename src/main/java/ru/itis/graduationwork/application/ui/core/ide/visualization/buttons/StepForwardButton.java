package ru.itis.graduationwork.application.ui.core.ide.visualization.buttons;

import ru.itis.graduationwork.application.managers.content.IconsManager;
import ru.itis.graduationwork.application.managers.project.VisualizationSceneController;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.ide.IdeFramesIconsConstants;
import ru.itis.graduationwork.application.ui.core.templates.Button;

import java.awt.event.ActionEvent;

public class StepForwardButton extends Button {

    public StepForwardButton(){
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
        button.setIcon(IconsManager.getImageIcon(Image.NEXT,
                IdeFramesIconsConstants.VISUALIZATION_ICON_BUTTON_WIDTH,
                IdeFramesIconsConstants.VISUALIZATION_ICON_BUTTON_HEIGHT));
    }

    private void setToolTipText(){
        button.setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.step-forward.tooltip-text"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VisualizationSceneController.nextStep();
    }

}
