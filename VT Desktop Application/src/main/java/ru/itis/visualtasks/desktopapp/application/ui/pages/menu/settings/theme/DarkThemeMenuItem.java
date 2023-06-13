package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings.theme;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.SettingsManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.MenuItem;

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
