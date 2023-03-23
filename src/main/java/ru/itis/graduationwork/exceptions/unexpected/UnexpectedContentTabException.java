package ru.itis.graduationwork.exceptions.unexpected;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

@Slf4j
public class UnexpectedContentTabException extends SelfHandlingException {

    public UnexpectedContentTabException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleUnexpectedContentTabException);
        log.error(LoggingUtils.exceptionToString(this));
    }

}
