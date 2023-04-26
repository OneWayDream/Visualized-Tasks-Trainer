package ru.itis.visualtasks.desktopapp.exceptions.server;

import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class TaskUploadRequestExecutionException extends SelfHandlingException {

    public TaskUploadRequestExecutionException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleTaskUploadRequestExecutionException);
    }

}
