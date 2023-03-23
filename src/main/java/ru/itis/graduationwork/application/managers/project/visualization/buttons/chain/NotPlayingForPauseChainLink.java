package ru.itis.graduationwork.application.managers.project.visualization.buttons.chain;

import ru.itis.graduationwork.application.managers.project.visualization.buttons.VisualizationControlButtonsStatesManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.ide.visualization.buttons.VisualizationButtonType;

public class NotPlayingForPauseChainLink extends DisableReasonChainLink{

    public NotPlayingForPauseChainLink(){
        super();
        reason = LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.not-playing-for-pause.tooltip-text");
    }

    @Override
    protected boolean checkCondition(VisualizationButtonType buttonType) {
        return isPauseButton(buttonType) && isSceneStatic();
    }

    private boolean isPauseButton(VisualizationButtonType buttonType){
        return buttonType == VisualizationButtonType.PAUSE;
    }

    private boolean isSceneStatic(){
        return !VisualizationControlButtonsStatesManager.isScenePlaying();
    }

}
