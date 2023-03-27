package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings.background;

import ru.itis.visualtasks.desktopapp.application.managers.settings.BackgroundImageManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.MenuItem;

import java.awt.event.ActionEvent;

public class ResetBackgroundImageMenuItem extends MenuItem {

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-background.reset-image.text"));
        menuItem.setIcon(getImageIcon(Image.RESET));
        menuItem.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BackgroundImageManager.deleteBackgroundImage();
    }

}
