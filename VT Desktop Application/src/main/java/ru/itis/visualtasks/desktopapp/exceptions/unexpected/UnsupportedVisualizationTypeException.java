package ru.itis.visualtasks.desktopapp.exceptions.unexpected;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class UnsupportedVisualizationTypeException extends SelfHandlingException {

    public UnsupportedVisualizationTypeException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.unsupported-visualization-type-exception.message"));
        log.error(LoggingUtils.exceptionToString(this));
    }

}
