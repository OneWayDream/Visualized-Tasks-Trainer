package ru.itis.visualtasks.desktopapp.application.managers.server;

import ru.itis.visualtasks.desktopapp.exceptions.server.BannedTokenException;
import ru.itis.visualtasks.desktopapp.exceptions.server.ExpiredTokenException;
import ru.itis.visualtasks.desktopapp.exceptions.server.IncorrectTokenException;
import ru.itis.visualtasks.desktopapp.exceptions.server.SessionExpiredException;

import java.time.LocalDateTime;

public class TokensManager {

    public static String getAccessToken(){
        LocalDateTime expirationTime = AccountInformationManager.getAccessExpirationDate();
        if (expirationTime != null && expirationTime.isBefore(LocalDateTime.now())){
            updateAccessToken();
        }
        return AccountInformationManager.getAccessToken();
    }

    public static void updateAccessToken(){
        try {
            ServerAuthorizationManager.authenticate(getRefreshToken());
        } catch (ExpiredTokenException | BannedTokenException | IncorrectTokenException exception){
            throw new SessionExpiredException();
        }
    }

    public static String getRefreshToken(){
        LocalDateTime expirationTime = AccountInformationManager.getRefreshExpirationDate();
        if (expirationTime != null && expirationTime.isBefore(LocalDateTime.now())){
            throw new SessionExpiredException();
        }
        return AccountInformationManager.getRefreshToken();
    }

}
