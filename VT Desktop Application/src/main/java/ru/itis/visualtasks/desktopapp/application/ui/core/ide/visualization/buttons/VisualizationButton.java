package ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.buttons;

import ru.itis.visualtasks.desktopapp.application.managers.content.IconsManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.VisualizationControlButtonsManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.IdeFramesIconsConstants;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button;

public abstract class VisualizationButton extends Button {

    protected VisualizationButtonType visualizationButtonType;
    protected Image buttonIcon;

    public VisualizationButton(){
        super();
    }

    @Override
    protected void setButtonStyle() {
        disableDefaultButtonStyle();
        setIcon();
        setInitialButtonState();
        registerInControlButtonsManager();
    }

    protected void setIcon(){
        button.setIcon(IconsManager.getImageIcon(buttonIcon,
                IdeFramesIconsConstants.VISUALIZATION_ICON_BUTTON_WIDTH,
                IdeFramesIconsConstants.VISUALIZATION_ICON_BUTTON_HEIGHT));
    }

    protected void setToolTipText(String toolTipText){
        button.setToolTipText(toolTipText);
    }

    protected void setInitialButtonState(){
        button.setEnabled(false);
        handleButtonState();
    }

    protected void handleButtonState(){
        if (isButtonEnabled()){
            setEnabledTooltipText();
        } else {
            setDisabledTooltipText();
        }
    }

    protected boolean isButtonEnabled(){
        return button.isEnabled();
    }

    protected void setDisabledTooltipText(){
        setToolTipText(VisualizationControlButtonsManager.getDisableReason(visualizationButtonType));
    }

    protected abstract void setEnabledTooltipText();
    protected abstract void registerInControlButtonsManager();

    public void setEnabled(boolean isEnabled){
        button.setEnabled(isEnabled);
        handleButtonState();
    }

}
