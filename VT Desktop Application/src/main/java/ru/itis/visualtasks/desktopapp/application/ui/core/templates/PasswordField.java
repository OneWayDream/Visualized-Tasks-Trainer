package ru.itis.visualtasks.desktopapp.application.ui.core.templates;

import javax.swing.*;

public abstract class PasswordField implements Component {

    protected JPasswordField passwordField;

    protected PasswordField(){
        passwordField = new JPasswordField();
    }

    protected void createPasswordField(){
        setUpPasswordField();
    }

    protected abstract void setUpPasswordField();

    @Override
    public JComponent getComponent() {
        return passwordField;
    }

    public String  getPassword(){
        return String.valueOf(passwordField.getPassword());
    }

}
