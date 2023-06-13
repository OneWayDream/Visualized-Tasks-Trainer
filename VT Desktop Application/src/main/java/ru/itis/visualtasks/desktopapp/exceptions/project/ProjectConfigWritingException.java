package ru.itis.visualtasks.desktopapp.exceptions.project;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class ProjectConfigWritingException extends SelfHandlingException {

    public ProjectConfigWritingException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.project-config-writing-exception.message"));
        log.error(LoggingUtils.exceptionToString(this));
    }

}
