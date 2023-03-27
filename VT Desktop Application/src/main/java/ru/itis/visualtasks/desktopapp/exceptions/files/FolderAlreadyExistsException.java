package ru.itis.visualtasks.desktopapp.exceptions.files;

import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class FolderAlreadyExistsException extends SelfHandlingException {

    public FolderAlreadyExistsException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleFolderAlreadyExistsException);
    }

}
