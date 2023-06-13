package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.account;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.server.AuthorizationManager;
import ru.itis.visualtasks.desktopapp.application.managers.content.IconsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Menu;
import ru.itis.visualtasks.desktopapp.application.ui.pages.develop.DevelopPageFrame;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.MenuIconConstants;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.account.signin.SignInMenuItem;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.account.upload.TaskUploadMenuItem;

import javax.swing.*;

public class AccountMenu extends Menu {

    @Override
    protected void initMenu() {
        setMenuStyle();
        if (AuthorizationManager.isAuthorized()){
            menu.add(new AccountInfoLabel().getComponent());
            if (isDevelopPage()){
                menu.add(new JToolBar.Separator());
                menu.add(new TaskUploadMenuItem().getComponent());
            }
            menu.add(new JToolBar.Separator());
            menu.add(new LogOutMenuItem().getComponent());
        } else {
            menu.add(new SignInMenuItem().getComponent());
        }
    }

    private boolean isDevelopPage() {
        return Application.getCurrentPageFrame() instanceof DevelopPageFrame;
    }

    private void setMenuStyle(){
        menu.setText(LocalizationManager.getLocaleTextByKey("menu.account.text"));
        menu.setForeground(ColorsManager.getTextColor());
        menu.setIcon(getImageIcon());
    }

    private ImageIcon getImageIcon(){
        return IconsManager.getImageIcon(Image.ACCOUNT,
                MenuIconConstants.MENU_ICON_WIDTH,
                MenuIconConstants.MENU_ICON_HEIGHT);
    }

    public void update(){
        menu.removeAll();
        initMenu();
        menu.validate();
        menu.repaint();
    }

}
