package ru.itis.graduationwork.application.ui.core.templates;

import ru.itis.graduationwork.application.managers.IconsManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.pages.menu.MenuIconConstants;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class MenuItem implements Component, ActionListener {

    protected final JMenuItem menuItem;

    protected MenuItem(){
        menuItem = new JMenuItem();
        initMenuItem();
        menuItem.addActionListener(this);
    }

    protected abstract void initMenuItem();

    @Override
    public JComponent getComponent() {
        return menuItem;
    }

    protected ImageIcon getImageIcon(Image image){
        return IconsManager.getImageIcon(image,
                MenuIconConstants.MENU_ICON_WIDTH,
                MenuIconConstants.MENU_ICON_HEIGHT);
    }

}
