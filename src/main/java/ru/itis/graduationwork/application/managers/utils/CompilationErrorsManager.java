package ru.itis.graduationwork.application.managers.utils;

import ru.itis.graduationwork.application.Application;

import javax.swing.*;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CompilationErrorsManager {

    private static final ScheduledExecutorService delayedExecutorService = Executors.newScheduledThreadPool(1);

    public static void handleDiagnostics(String filePath, List<Diagnostic<? extends JavaFileObject>> diagnostics) {
        Diagnostic.Kind kind = diagnostics.get(0).getKind();
        long lineNumber = diagnostics.get(0).getLineNumber();
        long columnNumber = diagnostics.get(0).getColumnNumber();
        String errorMessage = getErrorMessage(diagnostics);
        String errorContent = prepareErrorContent(filePath, kind, lineNumber, columnNumber, errorMessage);
        addDelayedException(
                () -> CompilationErrorsManager.handleDiagnosticsError(errorContent)
        );
    }

    private static String getErrorMessage(List<Diagnostic<? extends JavaFileObject>> diagnostics){
        StringBuilder errorMessageBuilder = new StringBuilder();
        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics) {
            errorMessageBuilder.append(diagnostic.getMessage(null)).append(",");
        }
        return errorMessageBuilder.substring(0, errorMessageBuilder.length() - 1);
    }

    private static String prepareErrorContent(String filePath, Diagnostic.Kind kind, long lineNumber, long columnNumber, String errorMessage) {
        return "" + kind + " in " + filePath + ": " + errorMessage + " (" + lineNumber + ", " + columnNumber + ")";
    }

    private static void addDelayedException(Runnable task){
        delayedExecutorService.schedule(task, 200, TimeUnit.MILLISECONDS);
    }

    private static void handleDiagnosticsError(String errorMessage){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                errorMessage, "", JOptionPane.ERROR_MESSAGE);
    }

}
