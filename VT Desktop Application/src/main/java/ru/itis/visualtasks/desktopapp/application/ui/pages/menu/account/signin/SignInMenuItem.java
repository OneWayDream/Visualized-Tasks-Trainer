package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.account.signin;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.MenuItem;

import java.awt.event.ActionEvent;

public class SignInMenuItem extends MenuItem {

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.account.sign-in.text"));
        menuItem.setIcon(getImageIcon(Image.SIGN_IN));
        menuItem.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SignInDialog signInDialog = new SignInDialog();
        signInDialog.initDialog();
    }

}
