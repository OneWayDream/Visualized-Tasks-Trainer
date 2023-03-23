package ru.itis.graduationwork.exceptions.execution;

import ru.itis.graduationwork.exceptions.core.SelfHandlingException;

public class JavaFileCompilationException extends SelfHandlingException {

    public JavaFileCompilationException() {
        super();
    }

    @Override
    public void handle() {
        // all compilation errors will be handled JavaClassesCompiler.compileJavaClass with CompilationErrorsManager.
    }
}
