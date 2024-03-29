package ru.itis.visualtasks.desktopapp.exceptions.usersettings;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class BackgroundImageSavingException extends SelfHandlingException {

    public BackgroundImageSavingException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.background-image-saving-exception.message"));
        log.error(LoggingUtils.exceptionToString(this));
    }

}
