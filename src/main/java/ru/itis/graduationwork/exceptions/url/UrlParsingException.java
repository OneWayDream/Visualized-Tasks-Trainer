package ru.itis.graduationwork.exceptions.url;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

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
        ExceptionsManager.addDelayedException(ExceptionsManager::handleUrlParsingException);
        log.error(LoggingUtils.exceptionToString(this));
    }

}
