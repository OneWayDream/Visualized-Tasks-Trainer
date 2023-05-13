package ru.itis.visualtasks.desktopapp.exceptions.properties;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class RecentListReadingException extends SelfHandlingException {

    public RecentListReadingException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.recent-list-reading-exception.message"));
        log.error(LoggingUtils.exceptionToString(this));
    }

}
