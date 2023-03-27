package ru.itis.visualtasks.desktopapp.application.ui.core.templates;

import ru.itis.visualtasks.desktopapp.application.managers.content.IconsManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.MenuIconConstants;

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
