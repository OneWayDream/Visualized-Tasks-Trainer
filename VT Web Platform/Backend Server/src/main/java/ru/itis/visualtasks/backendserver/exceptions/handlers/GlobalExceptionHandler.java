package ru.itis.visualtasks.backendserver.exceptions.handlers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itis.visualtasks.backendserver.exceptions.jwtserver.JwtServerException;
import ru.itis.visualtasks.backendserver.exceptions.persistence.EntityNotExistsException;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<?> handlePersistenceException(PersistenceException exception){
        if (exception instanceof EntityNotExistsException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    (exception.getMessage() == null) ? "This entity is not exists" : exception.getMessage()
            );
        }
        if (exception instanceof EntityNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    (exception.getMessage() == null) ? "This entity is not found" : exception.getMessage()
            );
        } else {
            log.error(getStackTraceString(exception));
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Something went wrong");
        }
    }

    @ExceptionHandler(JwtServerException.class)
    public ResponseEntity<?> handleJwtServerException(JwtServerException exception){
        log.error(getStackTraceString(exception));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Something went wrong");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> defaultExceptionHandler(Exception exception){
        log.error(getStackTraceString(exception));
        return new ResponseEntity<>("HEEEEEEH?!?!? \n " +
                "(There is no reason for this exception, so I'm real'y teapot)", HttpStatus.I_AM_A_TEAPOT);
    }

    public static String getStackTraceString(Exception exception){
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        try{
            sw.close();
        } catch (Exception ex){
            //ignore
        }
        return exceptionAsString;
    }

}
