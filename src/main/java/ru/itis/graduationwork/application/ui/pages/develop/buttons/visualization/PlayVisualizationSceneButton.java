package ru.itis.graduationwork.application.ui.pages.develop.buttons.visualization;

import ru.itis.graduationwork.application.managers.IconsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.ide.IdeFramesIconsConstants;
import ru.itis.graduationwork.application.ui.core.templates.Button;

import java.awt.event.ActionEvent;

public class PlayVisualizationSceneButton extends Button {

    public PlayVisualizationSceneButton(){
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
        button.setIcon(IconsManager.getImageIcon(Image.PLAY,
                IdeFramesIconsConstants.VISUALIZATION_ICON_BUTTON_WIDTH,
                IdeFramesIconsConstants.VISUALIZATION_ICON_BUTTON_HEIGHT));
    }

    private void setToolTipText(){
        button.setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.play.tooltip-text"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Play the scene!");
    }

}
