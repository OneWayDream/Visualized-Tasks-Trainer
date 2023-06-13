package ru.itis.visualtasks.backendserver.exceptions.jwtserver;

public class JwtRegistrationException extends JwtServerException {
    public JwtRegistrationException() {
    }

    public JwtRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

}
