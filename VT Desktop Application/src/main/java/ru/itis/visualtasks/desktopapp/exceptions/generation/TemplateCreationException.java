package ru.itis.visualtasks.desktopapp.exceptions.generation;

import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

@Slf4j
public class TemplateCreationException extends SelfHandlingException {

    private String destinationPath;

    public TemplateCreationException(Throwable cause, String destinationPath) {
        super(cause);
        this.destinationPath = destinationPath;
    }

    public TemplateCreationException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        ExceptionsManager.addDelayedException(
                () -> ExceptionsManager.handleErrorExceptionWithLocalization("exceptions.template-loading-exception.message",
                        destinationPath));
        log.error(LoggingUtils.exceptionToString(this));
    }

}
