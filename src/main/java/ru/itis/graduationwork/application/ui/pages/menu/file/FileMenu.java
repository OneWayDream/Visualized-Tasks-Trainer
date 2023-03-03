package ru.itis.graduationwork.application.ui.pages.menu.file;

import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.IconsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.templates.Menu;
import ru.itis.graduationwork.application.ui.pages.menu.MenuIconConstants;
import ru.itis.graduationwork.application.ui.pages.menu.file.mainmenu.ToMainMenuItem;
import ru.itis.graduationwork.application.ui.pages.menu.file.newproject.CreateNewProjectMenuItem;
import ru.itis.graduationwork.application.ui.pages.menu.file.openproject.OpenProjectMenuItem;
import ru.itis.graduationwork.application.ui.pages.menu.file.recentproject.OpenRecentProjectMenuItem;

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
