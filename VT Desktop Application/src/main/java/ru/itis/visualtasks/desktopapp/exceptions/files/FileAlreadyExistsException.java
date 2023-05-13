package ru.itis.visualtasks.desktopapp.exceptions.files;

import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class FileAlreadyExistsException extends SelfHandlingException {

    public FileAlreadyExistsException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleWarningExceptionWithLocalization("exceptions.file-already-exists-exception.message"));
    }

}
