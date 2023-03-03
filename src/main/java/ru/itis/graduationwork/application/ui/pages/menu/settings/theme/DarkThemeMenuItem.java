package ru.itis.graduationwork.application.ui.pages.menu.settings.theme;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.managers.SettingsManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.settings.Theme;
import ru.itis.graduationwork.application.ui.core.templates.MenuItem;

import java.awt.event.ActionEvent;

public class DarkThemeMenuItem extends MenuItem {

    private final Theme THEME = Theme.DARK;

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-theme.dark-theme.text"));
        menuItem.setIcon(getImageIcon(Image.DARK_THEME));
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
