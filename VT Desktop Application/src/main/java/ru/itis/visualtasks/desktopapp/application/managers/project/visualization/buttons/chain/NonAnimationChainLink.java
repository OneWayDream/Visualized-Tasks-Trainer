package ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.chain;

import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.VisualizationControlButtonsStatesManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.buttons.VisualizationButtonType;

public class NonAnimationChainLink extends DisableReasonChainLink {

    public NonAnimationChainLink(){
        super();
        reason = LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.non-animation.tooltip-text");
    }

    @Override
    protected boolean checkCondition(VisualizationButtonType buttonType) {
        return checkIfActionsIsEmpty();
    }

    private boolean checkIfActionsIsEmpty(){
        return !VisualizationControlButtonsStatesManager.isAnyActions();
    }

}
