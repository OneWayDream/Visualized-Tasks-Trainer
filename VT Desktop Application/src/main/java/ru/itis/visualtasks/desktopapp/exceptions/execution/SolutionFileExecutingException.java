package ru.itis.visualtasks.desktopapp.exceptions.execution;

import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;
import ru.itis.visualtasks.desktopapp.utils.LoggingUtils;

public class SolutionFileExecutingException extends SelfHandlingException {

    private String consoleOutput;

    public SolutionFileExecutingException(Throwable cause) {
        super(cause);
    }

    public SolutionFileExecutingException(String consoleOutput) {
        super();
        this.consoleOutput = consoleOutput;
    }

    @Override
    public void handle() {
        if (consoleOutput == null){
            ExceptionsManager.handleSolutionFileExecutionException(LoggingUtils.exceptionToString(this));
        } else {
            ExceptionsManager.addDelayedException(() -> ExceptionsManager.handleSolutionFileExecutionException(consoleOutput));
        }
    }

}
