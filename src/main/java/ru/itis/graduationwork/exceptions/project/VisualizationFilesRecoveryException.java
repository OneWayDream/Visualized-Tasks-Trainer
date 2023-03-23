package ru.itis.graduationwork.exceptions.project;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

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
