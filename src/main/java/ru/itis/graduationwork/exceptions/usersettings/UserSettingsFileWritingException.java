package ru.itis.graduationwork.exceptions.usersettings;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

@Slf4j
public class UserSettingsFileWritingException extends SelfHandlingException {

    public UserSettingsFileWritingException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleUserSettingsFileWritingException);
        log.error(LoggingUtils.exceptionToString(this));
    }

}
