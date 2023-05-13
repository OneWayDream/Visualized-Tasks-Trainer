package ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.chain;

import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.VisualizationControlButtonsStatesManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.buttons.VisualizationButtonType;

public class NoNextStepsChainLink extends DisableReasonChainLink {

    public NoNextStepsChainLink(){
        super();
        reason = LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.no-next-action.tooltip-text");
    }

    @Override
    protected boolean checkCondition(VisualizationButtonType buttonType) {
        return isMoveForwardButton(buttonType) && checkIsAnyStepsAfter();
    }

    private boolean isMoveForwardButton(VisualizationButtonType buttonType) {
        return isStepForwardButton(buttonType) || isAtEndButton(buttonType) || isPlayButton(buttonType);
    }

    private boolean isStepForwardButton(VisualizationButtonType buttonType) {
        return buttonType == VisualizationButtonType.NEXT_STEP;
    }

    private boolean isAtEndButton(VisualizationButtonType buttonType) {
        return buttonType == VisualizationButtonType.AT_END;
    }

    private boolean isPlayButton(VisualizationButtonType buttonType) {
        return buttonType == VisualizationButtonType.PLAY;
    }

    private boolean checkIsAnyStepsAfter(){
        return !VisualizationControlButtonsStatesManager.isAnyNextActions();
    }

}
