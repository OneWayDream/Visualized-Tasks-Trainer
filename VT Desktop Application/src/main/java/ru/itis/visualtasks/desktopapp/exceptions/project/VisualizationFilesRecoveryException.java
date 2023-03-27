package ru.itis.visualtasks.desktopapp.exceptions.project;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class VisualizationFilesRecoveryException extends SelfHandlingException {

    public VisualizationFilesRecoveryException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleVisualizationFilesRecoveryException);
        log.error(LoggingUtils.exceptionToString(this));
    }

}
