package ru.itis.visualtasks.desktopapp.application.ui.pages.main.buttons;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.content.IconsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.MainFrameIconsConstants;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.dialogs.creation.ProjectCreationDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateNewProjectButton extends Button {

    public CreateNewProjectButton(){
        super();
        createButton();
    }

    @Override
    protected void setButtonStyle(){
        button.setPreferredSize(new Dimension(500, 90));
        button.setBorder(BorderFactory.createMatteBorder(2, 15, 2, 2,
                ColorsManager.getButtonBordersColor()));
        setIcon();
        setTextStyle();
    }

    private void setIcon(){
        button.setIcon(IconsManager.getImageIcon(Image.PLUS,
                MainFrameIconsConstants.LEFT_PANEL_BUTTON_ICON_WIDTH,
                MainFrameIconsConstants.LEFT_PANEL_BUTTON_ICON_HEIGHT));
    }

    private void setTextStyle(){
        button.setText(LocalizationManager.getLocaleTextByKey("main-frame.left-panel.buttons.create-new-project.text"));
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setIconTextGap(15);
        button.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ProjectCreationDialog projectCreationDialog = new ProjectCreationDialog();
        projectCreationDialog.initDialog();
    }

}
