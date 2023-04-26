package ru.itis.visualtasks.jwtserver.exceptions.auth;

public class IncorrectUserDataException extends AuthorizationException {
    public IncorrectUserDataException() {
    }

    public IncorrectUserDataException(Throwable cause) {
        super(cause);
    }
}
