package ru.itis.graduationwork.application.managers.project.visualization.buttons.chain;

import ru.itis.graduationwork.application.managers.project.visualization.buttons.VisualizationControlButtonsStatesManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.ide.visualization.buttons.VisualizationButtonType;

public class DisabledButtonChainLink extends DisableReasonChainLink {

    public DisabledButtonChainLink(){
        super();
        reason = LocalizationManager.getLocaleTextByKey("ide.content.visualization-scene.control-buttons.disabled-button.tooltip-text");
    }

    @Override
    protected boolean checkCondition(VisualizationButtonType buttonType) {
        return checkIfStartOrEndButtonAndDisabled(buttonType) || checkIfForwardOrBackStepButtonAndDisabled(buttonType)
                || checkIfPlayOrPauseButtonAndDisabled(buttonType);
    }


    private boolean checkIfStartOrEndButtonAndDisabled(VisualizationButtonType buttonType) {
        return (checkIfStartButton(buttonType) || checkIfEndButton(buttonType)) && checkIfStartAndEndButtonsDisabled();
    }

    private boolean checkIfStartButton(VisualizationButtonType buttonType) {
        return buttonType == VisualizationButtonType.AT_START;
    }

    private boolean checkIfEndButton(VisualizationButtonType buttonType) {
        return buttonType == VisualizationButtonType.AT_END;
    }

    private boolean checkIfStartAndEndButtonsDisabled() {
        return !VisualizationControlButtonsStatesManager.isStartEndButtonsEnabled();
    }


    private boolean checkIfForwardOrBackStepButtonAndDisabled(VisualizationButtonType buttonType) {
        return (checkIfForwardStepButton(buttonType) || checkIfBackStepButton(buttonType)) && checkIfForwardAndBackStepButtonsDisabled();
    }

    private boolean checkIfForwardStepButton(VisualizationButtonType buttonType) {
        return buttonType == VisualizationButtonType.NEXT_STEP;
    }

    private boolean checkIfBackStepButton(VisualizationButtonType buttonType) {
        return buttonType == VisualizationButtonType.PREVIOUS_STEP;
    }

    private boolean checkIfForwardAndBackStepButtonsDisabled() {
        return !VisualizationControlButtonsStatesManager.isForwardBackStepButtonsEnabled();
    }


    private boolean checkIfPlayOrPauseButtonAndDisabled(VisualizationButtonType buttonType) {
        return (checkIfPlayButton(buttonType) || checkIfPauseButton(buttonType)) && checkIfPauseAndPlayButtonsDisabled();
    }

    private boolean checkIfPlayButton(VisualizationButtonType buttonType) {
        return buttonType == VisualizationButtonType.PLAY;
    }

    private boolean checkIfPauseButton(VisualizationButtonType buttonType) {
        return buttonType == VisualizationButtonType.PAUSE;
    }

    private boolean checkIfPauseAndPlayButtonsDisabled() {
        return !VisualizationControlButtonsStatesManager.isPlayPauseButtonsEnabled();
    }

}
