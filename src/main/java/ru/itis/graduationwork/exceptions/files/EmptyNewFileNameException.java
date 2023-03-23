package ru.itis.graduationwork.exceptions.files;

import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;

public class EmptyNewFileNameException extends SelfHandlingException {

    public EmptyNewFileNameException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleEmptyNewFileNameException);
    }

}
