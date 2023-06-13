package ru.itis.visualtasks.desktopapp.exceptions.url;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class UrlParsingException extends SelfHandlingException {

    public UrlParsingException(Throwable cause) {
        super(cause);
    }

    public UrlParsingException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.url-parsing-exception.message"));
        log.error(LoggingUtils.exceptionToString(this));
    }

}
