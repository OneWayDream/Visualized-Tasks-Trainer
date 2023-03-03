package ru.itis.graduationwork.application.ui.pages.main.buttons;

import ru.itis.graduationwork.application.managers.*;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.settings.Link;
import ru.itis.graduationwork.application.ui.core.templates.Button;
import ru.itis.graduationwork.application.ui.pages.main.MainFrameIconsConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class OpenTasksListButton extends Button {

    public OpenTasksListButton(){
        super();
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setPreferredSize(new Dimension(200, 200));
        button.setBorder(BorderFactory.createLineBorder(ColorsManager.getButtonBordersColor(), 3));
        setIcon();
        setTextStyle();
    }

    private void setIcon(){
        button.setIcon(IconsManager.getImageIcon(Image.TASKS,
                MainFrameIconsConstants.RIGHT_PANEL_BUTTON_ICON_WIDTH,
                MainFrameIconsConstants.RIGHT_PANEL_BUTTON_ICON_HEIGHT));
    }

    private void setTextStyle(){
        button.setText(LocalizationManager.getLocaleTextByKey("main-frame.right-panel.buttons.open-tasks.text"));
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setIconTextGap(15);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.TOP);
        button.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Open tasks list");
        try{
            Desktop.getDesktop().browse(LinksManager.getLinkValue(Link.TASKS));
        } catch (IOException ex) {
            ExceptionsManager.handleUrlParsingException();
        }
    }
}
