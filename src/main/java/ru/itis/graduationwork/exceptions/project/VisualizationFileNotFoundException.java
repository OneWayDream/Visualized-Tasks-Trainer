package ru.itis.graduationwork.exceptions.project;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

@Slf4j
public class VisualizationFileNotFoundException extends SelfHandlingException {

    public VisualizationFileNotFoundException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleVisualizationFileNotFoundException);
        log.error(LoggingUtils.exceptionToString(this));
    }

}
