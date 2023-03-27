package ru.itis.visualtasks.desktopapp.exceptions.explorer;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class MonitoringThreadException extends SelfHandlingException {

    public MonitoringThreadException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleMonitoringThreadException);
        log.error(LoggingUtils.exceptionToString(this));
    }

}
