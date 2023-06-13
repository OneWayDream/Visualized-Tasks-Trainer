package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.actions.deletion;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Label;

import javax.swing.*;
import java.awt.*;

public class DeletionConfirmationLabel extends Label {

    public DeletionConfirmationLabel(){
        super();
        createLabel();
        label.doLayout();
    }

    @Override
    protected void setUpLabel() {
        setLabelContent();
        setLabelStyle();
    }

    private void setLabelContent(){
        label.setText(LocalizationManager.getLocaleTextByKey("ide.content.workspace.explorer.deletion-dialog.label.text"));
        label.setForeground(ColorsManager.getTextColor());
    }

    private void setLabelStyle(){
        setLabelPosition();
        setTextStyle();
    }

    private void setLabelPosition(){
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    private void setTextStyle(){
        label.setFont(new Font("Comic Sans", Font.ITALIC, 22));
    }

}
