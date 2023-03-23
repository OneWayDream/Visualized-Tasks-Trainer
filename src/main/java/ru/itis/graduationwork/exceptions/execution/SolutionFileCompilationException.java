package ru.itis.graduationwork.exceptions.execution;

import ru.itis.graduationwork.exceptions.core.SelfHandlingException;

public class SolutionFileCompilationException extends SelfHandlingException {

    public SolutionFileCompilationException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle() {
        // all compilation errors will be handled earlier.
    }

}
