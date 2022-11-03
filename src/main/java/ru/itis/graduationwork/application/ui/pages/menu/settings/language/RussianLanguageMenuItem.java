package ru.itis.graduationwork.application.ui.pages.menu.settings.language;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.SettingsManager;
import ru.itis.graduationwork.application.settings.units.Image;
import ru.itis.graduationwork.application.settings.units.Locale;
import ru.itis.graduationwork.application.ui.core.templates.MenuItem;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ModeComponentsSupplier;
import ru.itis.graduationwork.application.ui.pages.menu.MenuIconConstants;
import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RussianLanguageMenuItem extends MenuItem {

    private final Locale LOCALE = Locale.RU;

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-language.russian.text"));
        menuItem.setIcon(getImageIcon());
        menuItem.setForeground(ColorsManager.getTextColor());
        disableIfNeed();
    }

    private ImageIcon getImageIcon(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        return supplier.getImageIcon(Image.RUSSIA,
                MenuIconConstants.MENU_ICON_WIDTH,
                MenuIconConstants.MENU_ICON_HEIGHT);
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
