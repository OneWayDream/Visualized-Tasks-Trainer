package ru.itis.visualtasks.desktopapp.exceptions.files;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class FileGenerationException extends SelfHandlingException {

    private final String filePath;

    public FileGenerationException(String filePath) {
        super();
        this.filePath = filePath;
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.file-generation-exception.message", filePath)
        );
        log.error(LoggingUtils.exceptionToString(this));
    }
}
