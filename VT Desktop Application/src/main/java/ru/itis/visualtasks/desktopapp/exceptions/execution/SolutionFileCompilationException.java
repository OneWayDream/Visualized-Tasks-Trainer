package ru.itis.visualtasks.desktopapp.exceptions.execution;

import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class SolutionFileCompilationException extends SelfHandlingException {

    public SolutionFileCompilationException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        // all compilation errors will be handled earlier.
    }

}
