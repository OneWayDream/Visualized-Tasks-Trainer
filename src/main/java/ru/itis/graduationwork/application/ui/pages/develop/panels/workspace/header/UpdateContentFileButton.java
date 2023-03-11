package ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.header;

import ru.itis.graduationwork.application.managers.content.IconsManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.managers.project.WorkspaceContentManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.ide.IdeFramesIconsConstants;
import ru.itis.graduationwork.application.ui.core.templates.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UpdateContentFileButton extends Button {

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
