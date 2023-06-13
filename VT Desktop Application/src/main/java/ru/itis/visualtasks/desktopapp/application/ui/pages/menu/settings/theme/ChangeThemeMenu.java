package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings.theme;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Menu;

public class ChangeThemeMenu extends Menu {

    public ChangeThemeMenu(){
        super();
    }

    @Override
    protected void initMenu() {
        menu.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-theme.text"));
        menu.setForeground(ColorsManager.getTextColor());
        menu.setIcon(getImageIcon(Image.THEME));
        addMenuItems();
    }

    private void addMenuItems(){
        menu.add(new LightThemeMenuItem().getComponent());
        menu.add(new DarkThemeMenuItem().getComponent());
    }

}
