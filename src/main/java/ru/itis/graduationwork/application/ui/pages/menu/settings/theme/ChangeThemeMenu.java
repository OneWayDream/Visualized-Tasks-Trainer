package ru.itis.graduationwork.application.ui.pages.menu.settings.theme;

import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.templates.Menu;

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
