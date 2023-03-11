package ru.itis.graduationwork.application.ui.pages.menu.settings.language;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.managers.settings.SettingsManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.settings.Locale;
import ru.itis.graduationwork.application.ui.core.templates.MenuItem;

import java.awt.event.ActionEvent;

public class EnglishLanguageMenuItem extends MenuItem {

    private final Locale LOCALE = Locale.EN;

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-language.english.text"));
        menuItem.setIcon(getImageIcon(Image.UK));
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
