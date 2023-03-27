package ru.itis.visualtasks.desktopapp.application.ui.core.ide.tabs;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.content.IconsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.WorkspaceContentManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.IdeFramesIconsConstants;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.ContentTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OpenGeneralDescriptionButton extends Button {

    public OpenGeneralDescriptionButton(){
        super();
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
                ColorsManager.getButtonBordersColor()));
        setIcon();
        setTextStyle();
    }

    private void setIcon(){
        button.setIcon(IconsManager.getImageIcon(Image.GENERAL,
                IdeFramesIconsConstants.TAB_BUTTON_ICON_WIDTH,
                IdeFramesIconsConstants.TAB_BUTTON_ICON_HEIGHT));
    }

    private void setTextStyle(){
        button.setText(LocalizationManager.getLocaleTextByKey("ide.content.main-buttons.open-general-description-button.text"));
        button.setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.main-buttons.open-general-description-button.tooltip-text"));
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setIconTextGap(10);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WorkspaceContentManager.changeWorkspaceContent(ContentTab.GENERAL_DESCRIPTION);
    }

}
