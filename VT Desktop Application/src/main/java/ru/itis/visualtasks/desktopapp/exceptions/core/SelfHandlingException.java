package ru.itis.visualtasks.desktopapp.exceptions.core;

public abstract class SelfHandlingException extends RuntimeException {

    public SelfHandlingException() {
        super();
    }

    public SelfHandlingException(String message) {
        super(message);
    }

    public SelfHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelfHandlingException(Throwable cause) {
        super(cause);
    }

    protected SelfHandlingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public abstract void handle();
}
