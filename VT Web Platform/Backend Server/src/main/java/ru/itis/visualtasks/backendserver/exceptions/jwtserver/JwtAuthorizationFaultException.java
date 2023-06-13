package ru.itis.visualtasks.backendserver.exceptions.jwtserver;

public class JwtAuthorizationFaultException extends JwtServerException{

    public JwtAuthorizationFaultException(Throwable cause) {
        super(cause);
    }
}
