package ru.itis.visualtasks.desktopapp.exceptions.files;

import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class EmptyNewFileNameException extends SelfHandlingException {

    public EmptyNewFileNameException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleEmptyNewFileNameException);
    }

}
