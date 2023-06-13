package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.file.openproject;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.MenuItem;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.choosers.DirectoryAsProjectChooser;

import java.awt.event.ActionEvent;

public class OpenProjectMenuItem extends MenuItem {

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.file.open-project.text"));
        menuItem.setForeground(ColorsManager.getTextColor());
        menuItem.setIcon(getImageIcon(Image.FOLDER));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DirectoryAsProjectChooser chooser = new DirectoryAsProjectChooser();
        chooser.execute();
    }
}
