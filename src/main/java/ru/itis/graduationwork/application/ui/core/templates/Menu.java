package ru.itis.graduationwork.application.ui.core.templates;

import ru.itis.graduationwork.application.managers.IconsManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.pages.menu.MenuIconConstants;

import javax.swing.*;

public abstract class Menu implements Component{

    protected final JMenu menu;

    protected Menu(){
        menu = new JMenu();
        initMenu();
    }

    protected abstract void initMenu();

    @Override
    public JComponent getComponent() {
        return menu;
    }

    protected ImageIcon getImageIcon(Image image){
        return IconsManager.getImageIcon(image,
                MenuIconConstants.MENU_ICON_WIDTH,
                MenuIconConstants.MENU_ICON_HEIGHT);
    }

}
