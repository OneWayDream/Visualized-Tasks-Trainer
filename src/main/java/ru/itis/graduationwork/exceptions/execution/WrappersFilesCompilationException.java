package ru.itis.graduationwork.exceptions.execution;

import ru.itis.graduationwork.exceptions.core.SelfHandlingException;

public class WrappersFilesCompilationException extends SelfHandlingException {

    public WrappersFilesCompilationException() {
        super();
    }

    @Override
    public void handle() {
        // all compilation errors will be handled earlier.
    }

}
