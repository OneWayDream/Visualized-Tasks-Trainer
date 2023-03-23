package ru.itis.graduationwork.exceptions.execution;

import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.core.SelfHandlingException;
import ru.itis.graduationwork.utils.LoggingUtils;

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
