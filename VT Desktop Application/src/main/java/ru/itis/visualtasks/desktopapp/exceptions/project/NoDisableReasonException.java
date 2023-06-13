package ru.itis.visualtasks.desktopapp.exceptions.project;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class NoDisableReasonException extends SelfHandlingException {

    public NoDisableReasonException() {
        super();
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.no-disable-reason-exception.message"));
        log.error(LoggingUtils.exceptionToString(this));
    }

}
