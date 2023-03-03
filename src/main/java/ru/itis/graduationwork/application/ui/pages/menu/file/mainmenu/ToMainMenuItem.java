package ru.itis.graduationwork.application.ui.pages.menu.file.mainmenu;

import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.managers.PagesManager;
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
