package ru.itis.visualtasks.jwtserver.exceptions.token;

import ru.itis.visualtasks.jwtserver.exceptions.auth.AuthorizationException;

public class IncorrectJwtException extends AuthorizationException {

    public IncorrectJwtException() {
    }

    public IncorrectJwtException(Throwable cause) {
        super(cause);
    }

}
