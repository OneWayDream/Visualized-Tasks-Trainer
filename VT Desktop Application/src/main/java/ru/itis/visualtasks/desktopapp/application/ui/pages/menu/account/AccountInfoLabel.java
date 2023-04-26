package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.account;

import ru.itis.visualtasks.desktopapp.application.managers.server.AuthorizationManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Label;

import javax.swing.*;
import java.awt.*;

public class AccountInfoLabel extends Label {

    public AccountInfoLabel(){
        super();
        createLabel();
    }

    @Override
    protected void setUpLabel() {
        label.setForeground(ColorsManager.getTextColor());
        label.setFont(new Font("Comic Sans", Font.ITALIC, 14));
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(getLabelText());
    }

    private String getLabelText(){
        return String.format(
                LocalizationManager.getLocaleTextByKey("menu.account.account-info-label.text"),
                AuthorizationManager.getAccountName()
        );
    }

}
