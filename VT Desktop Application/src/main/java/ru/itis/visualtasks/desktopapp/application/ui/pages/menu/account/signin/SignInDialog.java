package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.account.signin;

import ru.itis.visualtasks.desktopapp.application.entities.server.UserAuthorizationForm;
import ru.itis.visualtasks.desktopapp.application.managers.server.AuthorizationManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Dialog;
import ru.itis.visualtasks.desktopapp.exceptions.server.EmptySignInFieldsException;

import java.awt.*;

public class SignInDialog extends Dialog {

    private UserLoginTextField userLoginTextField;
    private UserPasswordField userPasswordField;

    @Override
    protected void initFields() {
        width = getDeviceScreenWidth() / 3;
        height = getDeviceScreenHeight() / 3;
        title = LocalizationManager.getLocaleTextByKey("menu.account.sign-in.dialog.title");
    }

    @Override
    public void initDialog(){
        dialog.setLayout(new GridBagLayout());
        dialog.setResizable(false);
        super.initDialog();
    }

    @Override
    protected void addComponents() {
        addLoginTextFieldTitle();
        addLoginTextField();
        addPasswordFieldTitle();
        addPasswordField();
        addSignInButton();
    }

    private void addLoginTextFieldTitle(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.weightx = 0.3;
        constraint.insets = new Insets(50,40,0,30);
        dialog.add(new SignInTitle(
                LocalizationManager.getLocaleTextByKey("menu.account.sign-in.dialog.login-field.title") + ":"
        ).getComponent(), constraint);
    }

    private void addLoginTextField() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.weightx = 0.6;
        constraint.insets = new Insets(50, -170, 0, 0);
        userLoginTextField = new UserLoginTextField();
        dialog.add(userLoginTextField.getComponent(), constraint);
    }

    private void addPasswordFieldTitle(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.weightx = 0.3;
        constraint.insets = new Insets(30,40,0,30);
        dialog.add(new SignInTitle(
                LocalizationManager.getLocaleTextByKey("menu.account.sign-in.dialog.password-field.title") + ":"
        ).getComponent(), constraint);
    }

    private void addPasswordField() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.weightx = 0.6;
        constraint.insets = new Insets(30, -170, 0, 0);
        userPasswordField = new UserPasswordField();
        dialog.add(userPasswordField.getComponent(), constraint);
    }

    private void addSignInButton() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 1.0;
        c.gridwidth = 2;
        c.insets = new Insets(0,0,-30,0);
        dialog.add(new SignInButton(this).getComponent(), c);
    }

    public void signIn(){
        checkIfAnyFieldIsEmpty();
        AuthorizationManager.signIn(UserAuthorizationForm.builder()
                        .login(userLoginTextField.getText())
                        .password(userPasswordField.getPassword())
                .build());
    }

    private void checkIfAnyFieldIsEmpty() {
        if (userLoginTextField.getText().length() == 0 || userPasswordField.getPassword().length() == 0){
            throw new EmptySignInFieldsException();
        }
    }

    public void dispose(){
        dialog.dispose();
    }

}
