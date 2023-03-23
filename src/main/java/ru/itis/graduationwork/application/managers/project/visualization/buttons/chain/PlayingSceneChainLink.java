package ru.itis.graduationwork.application.managers.project.visualization.buttons.chain;

import ru.itis.graduationwork.application.managers.project.visualization.buttons.VisualizationControlButtonsStatesManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.ide.visualization.buttons.VisualizationButtonType;

public class PlayingSceneChainLink extends DisableReasonChainLink{

    public PlayingSceneChainLink(){
        super();
        reason = LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.playing-scene.tooltip-text");
    }

    @Override
    protected boolean checkCondition(VisualizationButtonType buttonType) {
        return notAPauseButton(buttonType) && isScenePlaying();
    }

    private boolean notAPauseButton(VisualizationButtonType buttonType) {
        return buttonType != VisualizationButtonType.PAUSE;
    }

    private boolean isScenePlaying(){
        return VisualizationControlButtonsStatesManager.isScenePlaying();
    }

}
