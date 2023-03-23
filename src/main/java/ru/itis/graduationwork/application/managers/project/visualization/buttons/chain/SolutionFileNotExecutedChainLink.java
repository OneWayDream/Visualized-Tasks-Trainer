package ru.itis.graduationwork.application.managers.project.visualization.buttons.chain;

import ru.itis.graduationwork.application.managers.project.visualization.buttons.VisualizationControlButtonsStatesManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.ide.visualization.buttons.VisualizationButtonType;

public class SolutionFileNotExecutedChainLink extends DisableReasonChainLink{

    public SolutionFileNotExecutedChainLink(){
        super();
        reason = LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.not-executed-solution-file.tooltip-text");
    }

    @Override
    protected boolean checkCondition(VisualizationButtonType buttonType) {
        return !VisualizationControlButtonsStatesManager.isSolutionFileExecuted();
    }

}
