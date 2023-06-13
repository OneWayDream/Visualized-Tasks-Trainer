package ru.itis.visualtasks.desktopapp.exceptions.execution;

import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class WrappersFilesCompilationException extends SelfHandlingException {

    public WrappersFilesCompilationException() {
        super();
    }

    @Override
    public void handle() {
        // all compilation errors will be handled earlier.
    }

}
