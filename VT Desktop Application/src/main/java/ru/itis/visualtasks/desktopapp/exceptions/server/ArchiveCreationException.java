package ru.itis.visualtasks.desktopapp.exceptions.server;

import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class ArchiveCreationException extends SelfHandlingException {

    public ArchiveCreationException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        printStackTrace();
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.archive-creation-exception.message"));
    }

}
