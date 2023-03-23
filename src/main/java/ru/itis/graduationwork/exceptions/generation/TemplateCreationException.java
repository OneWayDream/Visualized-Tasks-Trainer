package ru.itis.graduationwork.exceptions.generation;

import lombok.extern.slf4j.Slf4j;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

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
        ExceptionsManager.addDelayedException(() -> ExceptionsManager.handleTemplateCreationException(destinationPath));
        log.error(LoggingUtils.exceptionToString(this));
    }

}
