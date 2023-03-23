package ru.itis.graduationwork.exceptions.unexpected;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

@Slf4j
public class UnsupportedVisualizationTypeException extends SelfHandlingException {

    public UnsupportedVisualizationTypeException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleUnsupportedVisualizationTypeException);
        log.error(LoggingUtils.exceptionToString(this));
    }

}