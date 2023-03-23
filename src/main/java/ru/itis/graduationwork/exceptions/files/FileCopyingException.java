package ru.itis.graduationwork.exceptions.files;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

@Slf4j
public class FileCopyingException extends SelfHandlingException {

    public FileCopyingException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(ExceptionsManager::handleFileCopyingException);
        log.error(LoggingUtils.exceptionToString(this));
    }

}
