package ru.itis.visualtasks.desktopapp.exceptions.server;

import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class ApiDifferenceException extends SelfHandlingException {

    public ApiDifferenceException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.api-difference-exception.message"));
    }

}
