package ru.itis.visualtasks.desktopapp.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LoggingUtils {

    public static String exceptionToString(Exception exception){
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
