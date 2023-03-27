package ru.itis.visualtasks.desktopapp.exceptions.execution;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class PythonFilePreparationException extends SelfHandlingException {

    public PythonFilePreparationException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handlePythonFilePreparationException);
        log.error(LoggingUtils.exceptionToString(this));
    }

}
