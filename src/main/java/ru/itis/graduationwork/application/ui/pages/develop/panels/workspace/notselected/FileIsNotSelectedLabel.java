package ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.notselected;

import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.templates.Label;

import javax.swing.*;
import java.awt.*;

public class FileIsNotSelectedLabel extends Label {

    public FileIsNotSelectedLabel(){
        super();
        createLabel();
    }

    @Override
    protected void setUpLabel() {
        label.setText(LocalizationManager.getLocaleTextByKey("ide.content.workspace.choose-content-file-panel.file-not-selected-label.text"));
        label.setFont(new Font("Comic Sans", Font.ITALIC, 22));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(ColorsManager.getTextColor());
    }

}
