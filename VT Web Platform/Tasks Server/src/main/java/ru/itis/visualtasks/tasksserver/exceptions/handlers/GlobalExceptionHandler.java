package ru.itis.visualtasks.tasksserver.exceptions.handlers;

import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itis.visualtasks.tasksserver.exceptions.persistence.EntityNotExistsException;
import ru.itis.visualtasks.tasksserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.tasksserver.exceptions.tasks.*;
import ru.itis.visualtasks.tasksserver.exceptions.token.ExpiredJwtException;
import ru.itis.visualtasks.tasksserver.exceptions.token.IncorrectJwtException;
import ru.itis.visualtasks.tasksserver.exceptions.token.TokenAuthenticationException;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TokenAuthenticationException.class)
    public ResponseEntity<?> handleTokenAuthenticationException(TokenAuthenticationException exception){
        if (exception instanceof ExpiredJwtException){
            return ResponseEntity.status(HttpErrorStatus.EXPIRED_TOKEN.value()).body(
                    (exception.getMessage() == null) ? "The token is expired." : exception.getMessage()
            );
        }
        if (exception instanceof IncorrectJwtException){
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpErrorStatus.INCORRECT_TOKEN.value()).body(
                    (exception.getMessage() == null) ? "This token is not supported" : exception.getMessage()
            );
        }
        else {
            log.error(getStackTraceString(exception));
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException exception){
        log.warn(getStackTraceString(exception));
        return ResponseEntity.status(HttpErrorStatus.ACCESS_DENIED.value()).body("Access denied");
    }

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<?> handleTaskException(TaskException exception){
        log.error(getStackTraceString(exception));
        if (exception instanceof UnsupportedLanguageException){
            return ResponseEntity.status(HttpErrorStatus.UNSUPPORTED_LANGUAGE_ERROR.value())
                    .body("Unsupported programming language.");
        }
        else if (exception instanceof UnsupportedTaskExtensionException){
            return ResponseEntity.status(HttpErrorStatus.UNSUPPORTED_EXTENSION_ERROR.value())
                    .body("Unsupported task archive extension. Use .zip files.");
        }
        else if (exception instanceof UnsupportedVisualizationTypeException){
            return ResponseEntity.status(HttpErrorStatus.UNSUPPORTED_VISUALIZATION_TYPE_ERROR.value())
                    .body("Fault to read task archive.");
        }
        else if (exception instanceof TaskArchiveReadingException){
            return ResponseEntity.status(HttpErrorStatus.TASK_READING_ERROR.value())
                    .body("Fault to read task archive.");
        }
        else if (exception instanceof ConfigFileReadingException){
            return ResponseEntity.status(HttpErrorStatus.CONFIG_READING_ERROR.value())
                    .body("Fault to read config file.");
        }
        else if (exception instanceof ConfigNotFoundException){
            return ResponseEntity.status(HttpErrorStatus.CONFIG_NOT_FOUND_ERROR.value())
                    .body("Config not found.");
        }
        else if (exception instanceof IncompleteConfigException){
            return ResponseEntity.status(HttpErrorStatus.INCOMPLETE_CONFIG_ERROR.value())
                    .body("Config file contains empty fields.");
        }
        else {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<?> handlePersistenceException(PersistenceException exception){
        if (exception instanceof EntityNotExistsException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This entity is not exists: "
                    +  ((exception.getMessage() == null) ? "" : exception.getMessage()));
        }
        if (exception instanceof EntityNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This entity is not found: "
                    +  ((exception.getMessage() == null) ? "" : exception.getMessage()));
        }
        else {
            log.error(getStackTraceString(exception));
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> defaultExceptionHandler(Exception exception){
        log.error(getStackTraceString(exception));
        return new ResponseEntity<>("HEEEEEEH?!?!? \n " +
                "(There is no reason for this exception, so I'm real'y teapot)", HttpStatus.I_AM_A_TEAPOT);
    }

    protected String getStackTraceString(Exception exception){
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
