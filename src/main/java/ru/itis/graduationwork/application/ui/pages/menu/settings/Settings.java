package ru.itis.graduationwork.application.ui.pages.menu.settings;

import ru.itis.graduationwork.application.settings.units.Image;
import ru.itis.graduationwork.application.ui.core.templates.Menu;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ModeComponentsSupplier;
import ru.itis.graduationwork.application.ui.pages.menu.MenuIconConstants;
import ru.itis.graduationwork.application.ui.pages.menu.settings.language.ChangeLanguageMenu;
import ru.itis.graduationwork.application.ui.pages.menu.settings.theme.ChangeThemeMenu;
import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;

import javax.swing.*;

public class Settings extends Menu {

    public Settings(){
        super();
    }

    @Override
    protected void initMenu() {
        setMenuStyle();
        menu.add(new ChangeThemeMenu().getComponent());
        menu.add(new ChangeLanguageMenu().getComponent());
    }

    private void setMenuStyle(){
        menu.setText(LocalizationManager.getLocaleTextByKey("menu.settings.text"));
        menu.setForeground(ColorsManager.getTextColor());
        menu.setIcon(getImageIcon());
    }

    private ImageIcon getImageIcon(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        return supplier.getImageIcon(Image.SETTINGS,
                MenuIconConstants.MENU_ICON_WIDTH,
                MenuIconConstants.MENU_ICON_HEIGHT);
    }

}
