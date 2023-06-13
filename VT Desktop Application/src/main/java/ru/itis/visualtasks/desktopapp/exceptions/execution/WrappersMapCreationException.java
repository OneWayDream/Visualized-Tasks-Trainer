package ru.itis.visualtasks.desktopapp.exceptions.execution;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class WrappersMapCreationException extends SelfHandlingException {

    public WrappersMapCreationException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.wrappers-map-creation-exception.message"));
        log.error(LoggingUtils.exceptionToString(this));
    }
}
