package ru.itis.visualtasks.desktopapp.exceptions.execution;

import ru.itis.visualtasks.desktopapp.exceptions.core.SelfHandlingException;

public class JavaFileCompilationException extends SelfHandlingException {

    public JavaFileCompilationException() {
        super();
    }

    @Override
    public void handle() {
        // all compilation errors will be handled JavaClassesCompiler.compileJavaClass with CompilationErrorsManager.
    }
}
