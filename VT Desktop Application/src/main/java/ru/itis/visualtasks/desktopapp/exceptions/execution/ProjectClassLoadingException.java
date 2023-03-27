package ru.itis.visualtasks.desktopapp.exceptions.execution;

import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class ProjectClassLoadingException extends SelfHandlingException {

    public ProjectClassLoadingException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {

    }


}
