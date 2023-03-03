package ru.itis.graduationwork.application.ui.pages.develop.buttons.tabs;

import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.IconsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.managers.WorkspaceContentManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.ide.IdeFramesIconsConstants;
import ru.itis.graduationwork.application.ui.core.templates.Button;
import ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.ContentTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OpenStudyingContentButton extends Button {

    public OpenStudyingContentButton(){
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
        button.setIcon(IconsManager.getImageIcon(Image.CONTENT,
                IdeFramesIconsConstants.TAB_BUTTON_ICON_WIDTH,
                IdeFramesIconsConstants.TAB_BUTTON_ICON_HEIGHT));
    }

    private void setTextStyle(){
        button.setText(LocalizationManager.getLocaleTextByKey("ide.content.main-buttons.open-studying-content.text"));
        button.setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.main-buttons.open-studying-content.tooltip-text"));
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setIconTextGap(10);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WorkspaceContentManager.changeWorkspaceContent(ContentTab.STUDYING_CONTENT);
    }

}
