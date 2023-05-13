package ru.itis.visualtasks.desktopapp.exceptions.files;

import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class EmptyNewFolderNameException extends SelfHandlingException {

    public EmptyNewFolderNameException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.empty-new-folder-name-exception.message"));
    }

}
