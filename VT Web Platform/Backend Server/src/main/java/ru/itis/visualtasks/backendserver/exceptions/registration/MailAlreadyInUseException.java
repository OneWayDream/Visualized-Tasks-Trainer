package ru.itis.visualtasks.backendserver.exceptions.registration;

public class MailAlreadyInUseException extends RegistrationException {

    public MailAlreadyInUseException(String message) {
        super(message);
    }

    public MailAlreadyInUseException(Throwable cause) {
        super(cause);
    }
}
