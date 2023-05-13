package ru.itis.visualtasks.desktopapp.application.managers.utils;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExceptionsManager {

    private static ScheduledExecutorService delayedExecutorService = Executors.newScheduledThreadPool(1);
    private static final long DEFAULT_DELAY_TIME_VALUE = 200;
    private static final TimeUnit DEFAULT_DELAY_TIME_UNIT = TimeUnit.MILLISECONDS;

    public static void resetExecutor(){
        delayedExecutorService = Executors.newScheduledThreadPool(1);
    }

    public static void addDelayedException(Runnable task){
        delayedExecutorService.schedule(task, DEFAULT_DELAY_TIME_VALUE, DEFAULT_DELAY_TIME_UNIT);
    }

    public static void addDelayedException(Runnable task, long time, TimeUnit timeUnit){
        delayedExecutorService.schedule(task, time, timeUnit);
    }

    public static void handleErrorExceptionWithLocalization(String messageKey){
        String message = prepareMessage(messageKey, null);
        handleException(JOptionPane.ERROR_MESSAGE, message);
    }

    public static void handleErrorExceptionWithLocalization(String messageKey, String messageArg){
        String message = prepareMessage(messageKey, messageArg);
        handleException(JOptionPane.ERROR_MESSAGE, message);
    }

    public static void handleErrorException(String message){
        handleException(JOptionPane.ERROR_MESSAGE, message);
    }

    public static void handleWarningExceptionWithLocalization(String messageKey){
        String message = prepareMessage(messageKey, null);
        handleException(JOptionPane.WARNING_MESSAGE, message);
    }

    public static void handleWarningExceptionWithLocalization(String messageKey, String messageArg){
        String message = prepareMessage(messageKey, messageArg);
        handleException(JOptionPane.WARNING_MESSAGE, message);
    }

    public static void handleInformationalException(String message){
        handleException(JOptionPane.INFORMATION_MESSAGE, message);
    }

    public static void handleInformationalExceptionWithLocalization(String messageKey){
        String message = prepareMessage(messageKey, null);
        handleException(JOptionPane.INFORMATION_MESSAGE, message);
    }

    public static void handleInformationalExceptionWithLocalization(String messageKey, String messageArg){
        String message = prepareMessage(messageKey, messageArg);
        handleException(JOptionPane.INFORMATION_MESSAGE, message);
    }

    private static String prepareMessage(String messageKey, String messageArg) {
        return (messageArg == null) ? LocalizationManager.getLocaleTextByKey(messageKey)
                : String.format(LocalizationManager.getLocaleTextByKey(messageKey), messageArg);
    }

    private static void handleException(int messageType, String message){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                message,
                "", messageType);
    }

}
