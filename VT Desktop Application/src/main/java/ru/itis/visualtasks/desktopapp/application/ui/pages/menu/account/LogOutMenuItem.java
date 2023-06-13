package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.account;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.server.AuthorizationManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.MenuItem;
import ru.itis.visualtasks.desktopapp.exceptions.server.ServerLogOutException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LogOutMenuItem extends MenuItem {

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.account.log-out.text"));
        menuItem.setIcon(getImageIcon(Image.LOGOUT));
        menuItem.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            AuthorizationManager.logOut();
            JOptionPane.showMessageDialog(
                    Application.getCurrentPageFrame().getComponent(),
                    LocalizationManager.getLocaleTextByKey("menu.account.log-out.success-text"),
                    "", JOptionPane.INFORMATION_MESSAGE);
        } catch (ServerLogOutException exception){
            exception.handle();
        }
    }

}
