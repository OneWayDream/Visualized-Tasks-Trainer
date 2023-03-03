package ru.itis.graduationwork.application.ui.pages.main.dialogs.recent;

import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.IconsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.templates.Button;
import ru.itis.graduationwork.application.ui.pages.main.MainFrameIconsConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OpenRecentListButton extends Button {

    public OpenRecentListButton(){
        super();
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setPreferredSize(new Dimension(500, 90));
        button.setBorder(BorderFactory.createMatteBorder(2, 15, 2, 2,
                ColorsManager.getButtonBordersColor()));
        setIcon();
        setTextStyle();
    }

    private void setIcon(){
        button.setIcon(IconsManager.getImageIcon(Image.RECENT,
                MainFrameIconsConstants.LEFT_PANEL_BUTTON_ICON_WIDTH,
                MainFrameIconsConstants.LEFT_PANEL_BUTTON_ICON_HEIGHT));
    }

    private void setTextStyle(){
        button.setText(LocalizationManager.getLocaleTextByKey("main-frame.left-panel.buttons.open-recent-project.text"));
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setIconTextGap(15);
        button.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RecentListDialog recentListDialog = new RecentListDialog();
        recentListDialog.initDialog();
    }

}
