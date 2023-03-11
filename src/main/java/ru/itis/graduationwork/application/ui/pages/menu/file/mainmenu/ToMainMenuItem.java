package ru.itis.graduationwork.application.ui.pages.menu.file.mainmenu;

import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.managers.content.PagesManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.templates.MenuItem;

import java.awt.event.ActionEvent;

public class ToMainMenuItem extends MenuItem {

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.file.to-main-menu.text"));
        menuItem.setForeground(ColorsManager.getTextColor());
        menuItem.setIcon(getImageIcon(Image.MENU));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PagesManager.openStartPage();
    }

}
