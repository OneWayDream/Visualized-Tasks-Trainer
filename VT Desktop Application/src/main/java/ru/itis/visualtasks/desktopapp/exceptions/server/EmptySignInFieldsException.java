package ru.itis.visualtasks.desktopapp.exceptions.server;

import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class EmptySignInFieldsException extends SelfHandlingException {

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.empty-sign-in-fields-exception.message"));
    }

}
