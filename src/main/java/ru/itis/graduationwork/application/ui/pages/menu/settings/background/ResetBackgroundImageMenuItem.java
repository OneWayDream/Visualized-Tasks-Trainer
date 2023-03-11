package ru.itis.graduationwork.application.ui.pages.menu.settings.background;

import ru.itis.graduationwork.application.managers.settings.BackgroundImageManager;
import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.templates.MenuItem;

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
