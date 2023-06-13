package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.workspace.header;

import ru.itis.visualtasks.desktopapp.application.managers.content.IconsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.WorkspaceContentManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.IdeFramesIconsConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UpdateContentFileButton extends ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button {

    public UpdateContentFileButton(){
        super();
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        setIcon();
        button.setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.workspace.informational-panel-header.update-content-file-button.tooltip-text"));
    }

    private void setIcon(){
        button.setIcon(IconsManager.getImageIcon(Image.SWITCH,
                IdeFramesIconsConstants.WORKSPACE_INFORMATIONAL_HEADER_ICON_WIDTH,
                IdeFramesIconsConstants.WORKSPACE_INFORMATIONAL_HEADER_ICON_HEIGHT));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        WorkspaceContentManager.updateWorkspaceContent();
    }


}
