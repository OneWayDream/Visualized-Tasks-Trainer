package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.workspace.notselected;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Label;

import javax.swing.*;
import java.awt.*;

public class FileIsNotSelectedLabel extends Label {

    public FileIsNotSelectedLabel(){
        super();
        createLabel();
    }

    @Override
    protected void setUpLabel() {
        label.setText(LocalizationManager.getLocaleTextByKey("ide.content.workspace.choose-content-file-panel.file-not-selected-label.develop.text"));
        label.setFont(new Font("Comic Sans", Font.ITALIC, 22));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(ColorsManager.getTextColor());
    }

}
