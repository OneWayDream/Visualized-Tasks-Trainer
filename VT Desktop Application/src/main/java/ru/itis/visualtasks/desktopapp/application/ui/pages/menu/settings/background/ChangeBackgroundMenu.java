package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings.background;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Menu;

public class ChangeBackgroundMenu extends Menu {

    @Override
    protected void initMenu() {
        menu.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-background.text"));
        menu.setIcon(getImageIcon(Image.BACKGROUND));
        menu.setForeground(ColorsManager.getTextColor());
        addMenuItems();
    }

    private void addMenuItems(){
        menu.add(new SetBackgroundImageMenuItem().getComponent());
        menu.add(new ResetBackgroundImageMenuItem().getComponent());
    }
}
