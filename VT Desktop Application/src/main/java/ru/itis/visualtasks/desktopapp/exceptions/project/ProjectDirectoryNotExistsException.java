package ru.itis.visualtasks.desktopapp.exceptions.project;

import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class ProjectDirectoryNotExistsException extends SelfHandlingException {

    public ProjectDirectoryNotExistsException(String message) {
        super(message);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleWarningExceptionWithLocalization("exceptions.project-directory-not-exists-exception.message",
                        getMessage()));
    }

}
