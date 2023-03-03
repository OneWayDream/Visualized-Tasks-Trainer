package ru.itis.graduationwork.application.managers;

import ru.itis.graduationwork.application.Application;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExceptionsManager {

    private static final ScheduledExecutorService delayedExecutorService = Executors.newScheduledThreadPool(1);

    public static void addDelayedException(Runnable task, long time, TimeUnit timeUnit){
        delayedExecutorService.schedule(task, time, timeUnit);
    }

    public static void handleFileCreationException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.file-creation-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleFileCopyingException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.file-copying-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleFileNotFoundException(String filePath){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                String.format(
                        LocalizationManager.getLocaleTextByKey("exceptions.file-not-found-exception.message"),
                        filePath
                ),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleFileReadingException(String filePath){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                String.format(
                        LocalizationManager.getLocaleTextByKey("exceptions.file-reading-exception.message"),
                        filePath
                ),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleUnexpectedContentTabException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.unexpected-content-tab-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleNotPresentImageIconException(String image){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                String.format(
                        LocalizationManager.getLocaleTextByKey("exceptions.not-present-image-icon-exception.message"),
                        image
                ),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleBackgroundImageSavingException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.background-image-saving-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleUrlParsingException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.url-parsing-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleProjectCreationException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.url-parsing-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleProjectDirectoryNotExistsException(String projectPath){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                String.format(
                        LocalizationManager.getLocaleTextByKey("exceptions.project-directory-not-exists-exception.message"),
                        projectPath
                ),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleProjectConfigReadingException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.project-config-reading-exception.message"),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleProjectConfigWritingException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.project-config-writing-exception.message"),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleRecentListReadingException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.recent-list-reading-exception.message"),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleRecentListWritingException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.recent-list-writing-exception.message"),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleUserSettingsFileWritingException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.user-settings-file-writing-exception.message"),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleDirectoryExploreException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.directory-explore-exception.message"),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleNotPresentUserBackgroundImageException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.not-present-user-background-image-exception.message"),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleUnsupportedContentFileExtensionException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.unsupported-content-file-extension-exception.message"),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleFileAlreadyExistsException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.file-already-exists-exception.message"),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleMonitoringThreadException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.monitoring-thread-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleMonitoringRegistrationException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.monitoring-registration-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleFileWritingException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.file-writing-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

}
