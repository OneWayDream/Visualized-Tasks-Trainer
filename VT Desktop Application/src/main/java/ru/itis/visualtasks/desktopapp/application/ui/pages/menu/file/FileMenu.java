package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.file;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.content.IconsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Menu;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.MenuIconConstants;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.file.mainmenu.ToMainMenuItem;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.file.newproject.CreateNewProjectMenuItem;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.file.openproject.OpenProjectMenuItem;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.file.recentproject.OpenRecentProjectMenuItem;

import javax.swing.*;

public class FileMenu extends Menu {

    @Override
    protected void initMenu() {
        setMenuStyle();
        menu.add(new CreateNewProjectMenuItem().getComponent());
        menu.add(new OpenRecentProjectMenuItem().getComponent());
        menu.add(new OpenProjectMenuItem().getComponent());
        menu.add(new JToolBar.Separator());
        menu.add(new ToMainMenuItem().getComponent());
    }

    private void setMenuStyle(){
        menu.setText(LocalizationManager.getLocaleTextByKey("menu.file.text"));
        menu.setForeground(ColorsManager.getTextColor());
        menu.setIcon(getImageIcon());
    }

    private ImageIcon getImageIcon(){
        return IconsManager.getImageIcon(Image.FILE,
                MenuIconConstants.MENU_ICON_WIDTH,
                MenuIconConstants.MENU_ICON_HEIGHT);
    }

}
