package ru.itis.graduationwork.application.managers.project.visualization.buttons.chain;

import ru.itis.graduationwork.application.managers.project.visualization.buttons.VisualizationControlButtonsStatesManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.ide.visualization.buttons.VisualizationButtonType;

public class NoNextStepsChainLink extends DisableReasonChainLink {

    public NoNextStepsChainLink(){
        super();
        reason = LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.no-next-action.tooltip-text");
    }

    @Override
    protected boolean checkCondition(VisualizationButtonType buttonType) {
        return checkIsAnyStepsAfter();
    }

    private boolean checkIsAnyStepsAfter(){
        return !VisualizationControlButtonsStatesManager.isAnyNextActions();
    }

}
