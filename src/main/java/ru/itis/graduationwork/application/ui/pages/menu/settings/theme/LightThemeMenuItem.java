package ru.itis.graduationwork.application.ui.pages.menu.settings.theme;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.SettingsManager;
import ru.itis.graduationwork.application.settings.units.Image;
import ru.itis.graduationwork.application.settings.units.Theme;
import ru.itis.graduationwork.application.ui.core.templates.MenuItem;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ModeComponentsSupplier;
import ru.itis.graduationwork.application.ui.pages.menu.MenuIconConstants;
import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LightThemeMenuItem extends MenuItem {

    private final Theme THEME = Theme.LIGHT;

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-theme.light-theme.text"));
        menuItem.setIcon(getImageIcon());
        menuItem.setForeground(ColorsManager.getTextColor());
        disableIfNeed();
    }

    private ImageIcon getImageIcon(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        return supplier.getImageIcon(Image.LIGHT_THEME,
                MenuIconConstants.MENU_ICON_WIDTH,
                MenuIconConstants.MENU_ICON_HEIGHT);
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
