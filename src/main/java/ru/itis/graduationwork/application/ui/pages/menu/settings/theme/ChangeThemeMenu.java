package ru.itis.graduationwork.application.ui.pages.menu.settings.theme;

import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.Menu;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ComponentsSupplier;
import ru.itis.graduationwork.application.ui.pages.menu.MenuIconConstants;
import ru.itis.graduationwork.application.utils.ColorsManager;
import ru.itis.graduationwork.application.utils.LocalizationManager;

import javax.swing.*;

public class ChangeThemeMenu extends Menu {

    public ChangeThemeMenu(){
        super();
    }

    @Override
    protected void initMenu() {
        menu.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-theme.text"));
        menu.setForeground(ColorsManager.getTextColor());
        menu.setIcon(getImageIcon());
        addMenuItems();
    }

    private void addMenuItems(){
        menu.add(new LightThemeMenuItem().getComponent());
        menu.add(new DarkThemeMenuItem().getComponent());
    }

    private ImageIcon getImageIcon(){
        ComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        return supplier.getImageIcon(Image.THEME,
                MenuIconConstants.MENU_ICON_WIDTH,
                MenuIconConstants.MENU_ICON_HEIGHT);
    }

}
