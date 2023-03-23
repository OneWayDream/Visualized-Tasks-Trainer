package ru.itis.graduationwork.exceptions.execution;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

@Slf4j
public class SolutionFileReadingException extends SelfHandlingException {

    public SolutionFileReadingException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleSolutionFileReadingException);
        log.error(LoggingUtils.exceptionToString(this));
    }
}
