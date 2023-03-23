package ru.itis.graduationwork.application.managers.project.visualization.buttons.chain;

import ru.itis.graduationwork.application.managers.project.visualization.buttons.VisualizationControlButtonsStatesManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.ide.visualization.buttons.VisualizationButtonType;

public class NoPreviousStepsChainLink extends DisableReasonChainLink {

    public NoPreviousStepsChainLink(){
        super();
        reason = LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.no-previous-action.tooltip-text");
    }

    @Override
    protected boolean checkCondition(VisualizationButtonType buttonType) {
        return checkIsAnyStepsBefore();
    }

    private boolean checkIsAnyStepsBefore(){
        return !VisualizationControlButtonsStatesManager.isAnyPreviousActions();
    }
}
