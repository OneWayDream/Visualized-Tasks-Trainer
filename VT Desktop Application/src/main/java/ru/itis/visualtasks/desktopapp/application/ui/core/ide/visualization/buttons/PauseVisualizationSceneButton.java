package ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.buttons;

import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.VisualizationControlButtonsManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationSceneController;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;

import java.awt.event.ActionEvent;

public class PauseVisualizationSceneButton extends VisualizationButton {

    public PauseVisualizationSceneButton(){
        super();
        visualizationButtonType = VisualizationButtonType.PAUSE;
        buttonIcon = Image.PAUSE;
        createButton();
    }

    @Override
    protected void setEnabledTooltipText() {
        setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.pause.tooltip-text"));
    }

    @Override
    protected void registerInControlButtonsManager() {
        VisualizationControlButtonsManager.setPauseVisualizationSceneButton(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VisualizationSceneController.pause();
    }

}
