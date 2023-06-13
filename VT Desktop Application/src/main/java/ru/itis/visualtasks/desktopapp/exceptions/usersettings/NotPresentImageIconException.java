package ru.itis.visualtasks.desktopapp.exceptions.usersettings;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class NotPresentImageIconException extends SelfHandlingException {

    public NotPresentImageIconException(String message) {
        super(message);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleWarningExceptionWithLocalization("exceptions.not-present-image-icon-exception.message",
                        getMessage()));
        log.error(LoggingUtils.exceptionToString(this));
    }

}
