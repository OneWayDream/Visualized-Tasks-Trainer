package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.account.signin;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button;
import ru.itis.visualtasks.desktopapp.exceptions.server.AuthenticationRequestExecutionException;
import ru.itis.visualtasks.desktopapp.exceptions.server.BannedAccountException;
import ru.itis.visualtasks.desktopapp.exceptions.server.EmptySignInFieldsException;
import ru.itis.visualtasks.desktopapp.exceptions.server.IncorrectUserDataException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SignInButton extends Button {

    private final SignInDialog parentDialog;

    public SignInButton(SignInDialog signInDialog){
        super();
        parentDialog = signInDialog;
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setText(LocalizationManager.getLocaleTextByKey("menu.account.sign-in.dialog.button.text"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            parentDialog.signIn();
            JOptionPane.showMessageDialog(
                    Application.getCurrentPageFrame().getComponent(),
                    LocalizationManager.getLocaleTextByKey("menu.account.sign-in.dialog.success-text"),
                    "", JOptionPane.INFORMATION_MESSAGE);
            parentDialog.dispose();
        } catch (BannedAccountException | IncorrectUserDataException
                 | AuthenticationRequestExecutionException | EmptySignInFieldsException exception){
            exception.handle();
        }
    }

}
