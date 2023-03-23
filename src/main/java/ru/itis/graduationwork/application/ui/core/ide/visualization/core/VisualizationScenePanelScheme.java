package ru.itis.graduationwork.application.ui.core.ide.visualization.core;

import lombok.Getter;
import ru.itis.graduationwork.application.ui.core.templates.Component;

public abstract class VisualizationScenePanelScheme implements Component {

    @Getter
    protected boolean isStartEndButtonsEnabled;
    @Getter
    protected boolean isForwardBackStepButtonsEnabled;
    @Getter
    protected boolean isPlayPauseButtonsEnabled;

    @Getter
    protected String inInitialStateCommand;
    @Getter
    protected long initialStepDelay;
    @Getter
    protected String atSceneStartCommand;
    @Getter
    protected long atStartStepDelay;
    @Getter
    protected String atSceneEndCommand;
    @Getter
    protected long atEndStepDelay;

    protected abstract void adjustControlButtons();
    protected abstract void adjustEdgesCommands();

    protected abstract void addComponents();

}
