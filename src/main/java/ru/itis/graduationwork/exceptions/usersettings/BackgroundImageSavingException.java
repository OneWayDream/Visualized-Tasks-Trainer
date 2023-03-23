package ru.itis.graduationwork.exceptions.usersettings;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

@Slf4j
public class BackgroundImageSavingException extends SelfHandlingException {

    public BackgroundImageSavingException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleBackgroundImageSavingException);
        log.error(LoggingUtils.exceptionToString(this));
    }

}
