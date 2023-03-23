package ru.itis.graduationwork.exceptions.execution;

import ru.itis.graduationwork.exceptions.core.SelfHandlingException;

public class ProjectClassLoadingException extends SelfHandlingException {

    public ProjectClassLoadingException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {

    }


}
