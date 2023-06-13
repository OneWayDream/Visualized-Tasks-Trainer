package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.account.signin;

import ru.itis.visualtasks.desktopapp.application.ui.core.templates.TextField;

import java.awt.*;

public class UserLoginTextField extends TextField {

    public UserLoginTextField(){
        super();
        createTextField();
    }

    @Override
    protected void setUpTextField() {
        textField.setPreferredSize(new Dimension(300, 30));
    }

}
