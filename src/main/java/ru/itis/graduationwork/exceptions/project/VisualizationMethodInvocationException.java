package ru.itis.graduationwork.exceptions.project;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

@Slf4j
public class VisualizationMethodInvocationException extends SelfHandlingException {

    public VisualizationMethodInvocationException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.handleVisualizationMethodInvocationException(LoggingUtils.exceptionToString(this));
        log.error(LoggingUtils.exceptionToString(this));
    }

}
