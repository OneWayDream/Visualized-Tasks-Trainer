package ru.itis.graduationwork.exceptions.files;

import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;

public class FolderAlreadyExistsException extends SelfHandlingException {

    public FolderAlreadyExistsException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleFolderAlreadyExistsException);
    }

}
