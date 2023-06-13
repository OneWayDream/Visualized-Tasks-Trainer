package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings.background;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.MenuItem;

import java.awt.event.ActionEvent;

public class SetBackgroundImageMenuItem extends MenuItem {

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-background.set-image.text"));
        menuItem.setIcon(getImageIcon(Image.IMAGE));
        menuItem.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BackgroundImageChooser chooser = new BackgroundImageChooser();
        chooser.execute();
        Application.reloadPage();
    }

}
