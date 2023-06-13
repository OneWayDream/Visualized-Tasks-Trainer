package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings.language;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.SettingsManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.MenuItem;

import java.awt.event.ActionEvent;

public class RussianLanguageMenuItem extends MenuItem {

    private final Locale LOCALE = Locale.RU;

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-language.russian.text"));
        menuItem.setIcon(getImageIcon(Image.RUSSIA));
        menuItem.setForeground(ColorsManager.getTextColor());
        disableIfNeed();
    }

    private void disableIfNeed(){
        if (getCurrentLocale() == LOCALE){
            menuItem.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getCurrentLocale() != LOCALE){
            Application.changeLocale(LOCALE);
        }
    }

    private Locale getCurrentLocale(){
        return SettingsManager.getLocale();
    }

}
