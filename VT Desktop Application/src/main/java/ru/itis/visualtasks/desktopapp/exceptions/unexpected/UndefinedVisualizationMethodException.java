package ru.itis.visualtasks.desktopapp.exceptions.unexpected;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class UndefinedVisualizationMethodException extends SelfHandlingException {

    public UndefinedVisualizationMethodException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.handleUndefinedVisualizationMethodException(LoggingUtils.exceptionToString(this));
        log.error(LoggingUtils.exceptionToString(this));
    }

}
