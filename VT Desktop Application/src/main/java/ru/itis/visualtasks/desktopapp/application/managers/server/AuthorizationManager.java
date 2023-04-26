package ru.itis.visualtasks.desktopapp.application.managers.server;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.entities.server.UserAuthorizationForm;

public class AuthorizationManager {

    public static boolean isAuthorized(){
        return AccountInformationManager.isUserAuthorized();
    }

    public static void logOut(){
        ServerAuthorizationManager.logOut(
                AccountInformationManager.getAccessToken(),
                AccountInformationManager.getRefreshToken()
        );
        Application.getCurrentPageFrame().updateMenu();

    }

    public static void signIn(UserAuthorizationForm userAuthorizationForm){
        ServerAuthorizationManager.signIn(userAuthorizationForm);
        ServerAuthorizationManager.authenticate(AccountInformationManager.getRefreshToken());
        Application.getCurrentPageFrame().updateMenu();

    }

    public static String getAccountName(){
        return AccountInformationManager.getAccountLogin();
    }

}
