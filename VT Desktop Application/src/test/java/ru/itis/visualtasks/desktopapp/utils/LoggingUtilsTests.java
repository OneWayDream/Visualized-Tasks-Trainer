package ru.itis.visualtasks.desktopapp.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class LoggingUtilsTests {

    @Test
    public void simple_exception_to_string(){
        Exception exception = new RuntimeException();
        Assertions.assertTrue(LoggingUtils.exceptionToString(exception).startsWith("java.lang.RuntimeException"));
    }

    @Test
    public void exception_with_message_to_string(){
        Exception exception = new RuntimeException("message");
        Assertions.assertTrue(LoggingUtils.exceptionToString(exception).startsWith("java.lang.RuntimeException: message"));
    }

}
