package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.file.newproject;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.MenuItem;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.dialogs.creation.ProjectCreationDialog;

import java.awt.event.ActionEvent;

public class CreateNewProjectMenuItem extends MenuItem {

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.file.create-new-project.text"));
        menuItem.setForeground(ColorsManager.getTextColor());
        menuItem.setIcon(getImageIcon(Image.NEW_PROJECT));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectCreationDialog projectCreationDialog = new ProjectCreationDialog();
        projectCreationDialog.initDialog();
    }
}
