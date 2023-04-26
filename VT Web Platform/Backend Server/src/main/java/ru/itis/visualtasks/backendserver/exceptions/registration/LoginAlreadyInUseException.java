package ru.itis.visualtasks.backendserver.exceptions.registration;

public class LoginAlreadyInUseException extends RegistrationException {

    public LoginAlreadyInUseException(String message) {
        super(message);
    }

    public LoginAlreadyInUseException(Throwable cause) {
        super(cause);
    }
}
