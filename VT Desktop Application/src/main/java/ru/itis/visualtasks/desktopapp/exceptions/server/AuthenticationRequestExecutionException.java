package ru.itis.visualtasks.desktopapp.exceptions.server;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class AuthenticationRequestExecutionException extends SelfHandlingException {

    public AuthenticationRequestExecutionException() {
        super();
    }

    public AuthenticationRequestExecutionException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.authentication-request-execution-exception.message"));
        log.error(LoggingUtils.exceptionToString(this));
    }

}
