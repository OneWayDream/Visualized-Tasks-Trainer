package ru.itis.graduationwork.application.ui.pages.menu.settings.theme;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.managers.settings.SettingsManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.settings.Theme;
import ru.itis.graduationwork.application.ui.core.templates.MenuItem;

import java.awt.event.ActionEvent;

public class LightThemeMenuItem extends MenuItem {

    private final Theme THEME = Theme.LIGHT;

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-theme.light-theme.text"));
        menuItem.setIcon(getImageIcon(Image.LIGHT_THEME));
        menuItem.setForeground(ColorsManager.getTextColor());
        disableIfNeed();
    }

    private void disableIfNeed(){
        if (getCurrentTheme() == THEME){
            menuItem.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getCurrentTheme() != THEME){
            Application.changeTheme(THEME);
        }
    }

    private Theme getCurrentTheme(){
        return SettingsManager.getTheme();
    }

}
