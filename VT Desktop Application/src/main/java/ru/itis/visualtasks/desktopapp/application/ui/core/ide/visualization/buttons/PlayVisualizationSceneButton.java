package ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.buttons;

import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.VisualizationControlButtonsManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationSceneController;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;

import java.awt.event.ActionEvent;

public class PlayVisualizationSceneButton extends VisualizationButton {

    public PlayVisualizationSceneButton(){
        super();
        visualizationButtonType = VisualizationButtonType.PLAY;
        buttonIcon = Image.PLAY;
        createButton();
    }

    @Override
    protected void setEnabledTooltipText() {
        setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.play.tooltip-text"));
    }

    @Override
    protected void registerInControlButtonsManager() {
        VisualizationControlButtonsManager.setPlayVisualizationSceneButton(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VisualizationSceneController.play();
    }

}
