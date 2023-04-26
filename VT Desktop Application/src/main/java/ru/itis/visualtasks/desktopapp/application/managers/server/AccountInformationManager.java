package ru.itis.visualtasks.desktopapp.application.managers.server;

import ru.itis.visualtasks.desktopapp.application.entities.project.AuthorizationInfo;
import ru.itis.visualtasks.desktopapp.application.entities.server.AuthenticationInformation;
import ru.itis.visualtasks.desktopapp.application.entities.server.SignInInformation;
import ru.itis.visualtasks.desktopapp.application.loaders.AuthorizationInfoLoader;
import ru.itis.visualtasks.desktopapp.exceptions.server.UserNotAuthorizedException;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.UserSettingsFileReadingException;

import java.time.LocalDateTime;

public class AccountInformationManager {

    private static AuthorizationInfo authorizationInfo;

    static {
        try {
            authorizationInfo = AuthorizationInfoLoader.getAuthorizationInfo();
            if (authorizationInfo == null){
                authorizationInfo = AuthorizationInfoLoader.getDefaultAuthorizationInfo();
            }
        } catch (UserSettingsFileReadingException exception){
            authorizationInfo = AuthorizationInfoLoader.getDefaultAuthorizationInfo();
        }
    }

    public static String getAccountLogin(){
        if (isUserAuthorized()){
            return authorizationInfo.getLogin();
        } else {
            throw new UserNotAuthorizedException();
        }
    }

    public static String getRefreshToken(){
        return authorizationInfo.getRefreshToken();
    }

    public static LocalDateTime getRefreshExpirationDate(){
        return (authorizationInfo.getRefreshExpirationDate() == null) ? null :
                LocalDateTime.parse(authorizationInfo.getRefreshExpirationDate());
    }

    public static String getAccessToken(){
        if (isUserAuthorized()){
            return authorizationInfo.getAccessToken();
        } else {
            throw new UserNotAuthorizedException();
        }
    }

    public static LocalDateTime getAccessExpirationDate(){
        if (isUserAuthorized()){
            return (authorizationInfo.getAccessExpirationDate() == null) ? null :
                    LocalDateTime.parse(authorizationInfo.getAccessExpirationDate());
        } else {
            throw new UserNotAuthorizedException();
        }
    }

    public static boolean isUserAuthorized() {
        return authorizationInfo != null && authorizationInfo.getAccessToken() != null;
    }

    public static void updateSignInInformation(SignInInformation signInInformation){
        authorizationInfo.setLogin(signInInformation.getLogin());
        authorizationInfo.setRefreshToken(signInInformation.getRefreshToken());
        authorizationInfo.setRefreshExpirationDate(signInInformation.getRefreshExpirationDate());
        AuthorizationInfoLoader.saveAuthorizationInfo(authorizationInfo);
    }

    public static void updateAuthenticationInformation(AuthenticationInformation authenticationInformation){
        authorizationInfo.setAccessToken(authenticationInformation.getAccessToken());
        authorizationInfo.setAccessExpirationDate(authenticationInformation.getAccessExpirationDate());
        AuthorizationInfoLoader.saveAuthorizationInfo(authorizationInfo);
    }

    public static void resetAuthenticationInformation(){
        authorizationInfo = AuthorizationInfoLoader.getDefaultAuthorizationInfo();
        AuthorizationInfoLoader.saveAuthorizationInfo(authorizationInfo);
    }

}
