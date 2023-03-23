package ru.itis.graduationwork.exceptions.execution;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

@Slf4j
public class WrappersMapCreationException extends SelfHandlingException {

    public WrappersMapCreationException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleWrappersMapCreationException);
        log.error(LoggingUtils.exceptionToString(this));
    }
}
