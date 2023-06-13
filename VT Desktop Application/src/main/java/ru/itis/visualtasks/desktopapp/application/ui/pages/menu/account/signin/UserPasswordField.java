package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.account.signin;

import ru.itis.visualtasks.desktopapp.application.ui.core.templates.PasswordField;

import java.awt.*;

public class UserPasswordField extends PasswordField {

    public UserPasswordField(){
        super();
        createPasswordField();
    }

    @Override
    protected void setUpPasswordField() {
        passwordField.setPreferredSize(new Dimension(300, 30));
    }

}
