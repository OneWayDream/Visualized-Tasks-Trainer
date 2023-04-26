package ru.itis.visualtasks.desktopapp.exceptions.server;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.server.AccountInformationManager;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class SessionExpiredException extends SelfHandlingException {

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleSessionExpiredException);
        AccountInformationManager.resetAuthenticationInformation();
        Application.getCurrentPageFrame().updateMenu();
    }

}
