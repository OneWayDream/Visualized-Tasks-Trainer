package ru.itis.graduationwork.exceptions.explorer;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

@Slf4j
public class MonitoringRegistrationException extends SelfHandlingException {

    public MonitoringRegistrationException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleMonitoringRegistrationException);
        log.error(LoggingUtils.exceptionToString(this));
    }

}
