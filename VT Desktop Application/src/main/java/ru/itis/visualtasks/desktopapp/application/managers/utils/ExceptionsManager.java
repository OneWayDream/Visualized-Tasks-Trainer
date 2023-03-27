package ru.itis.visualtasks.desktopapp.application.managers.utils;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExceptionsManager {

    private static final ScheduledExecutorService delayedExecutorService = Executors.newScheduledThreadPool(1);
    private static final long DEFAULT_DELAY_TIME_VALUE = 200;
    private static final TimeUnit DEFAULT_DELAY_TIME_UNIT = TimeUnit.MILLISECONDS;

    public static void addDelayedException(Runnable task){
        delayedExecutorService.schedule(task, DEFAULT_DELAY_TIME_VALUE, DEFAULT_DELAY_TIME_UNIT);
    }

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
                LocalizationManager.getLocaleTextByKey("exceptions.project-creation-exception.message"),
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

    public static void handleUnsupportedLanguageException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.unsupported-language-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleUnsupportedVisualizationTypeException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.unsupported-visualization-type-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleVisualizationFileNotFoundException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.visualization-file-not-found-exception.message"),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleVisualizationFilesRecoveryException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.visualization-file-recovery-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleWrapperFilesReadingException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.wrapper-file-reading-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleEmptyNewFileNameException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.empty-new-file-name-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleEmptyNewFolderNameException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.empty-new-folder-name-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleFileDeletionException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.file-deletion-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleFolderCreationException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.folder-creation-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleFolderAlreadyExistsException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.folder-already-exists-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleSolutionFileExecutionException(String exceptionContent){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                exceptionContent,
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleFileGenerationException(String filePath) {
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                String.format(
                        LocalizationManager.getLocaleTextByKey("exceptions.file-generation-exception.message"),
                        filePath
                ),
                "", JOptionPane.WARNING_MESSAGE);
    }

    public static void handleTemplateLoadingException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.template-loading-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleTemplateCreationException(String templatePath){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                String.format(
                        LocalizationManager.getLocaleTextByKey("exceptions.template-loading-exception.message"),
                        templatePath
                ),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleNoDisableReasonException(){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.no-disable-reason-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleUndefinedVisualizationMethodException(String exceptionStackTrace){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                exceptionStackTrace,
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleVisualizationMethodInvocationException(String exceptionStackTrace){
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                exceptionStackTrace,
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleWrappersMapCreationException() {
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.wrappers-map-creation-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleSolutionFileReadingException() {
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.solution-file-reading-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handlePythonSolutionScryptExecutionException(String consoleOutput) {
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                consoleOutput,
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void handlePythonFilePreparationException() {
        JOptionPane.showMessageDialog(
                Application.getCurrentPageFrame().getComponent(),
                LocalizationManager.getLocaleTextByKey("exceptions.python-file-preparation-exception.message"),
                "", JOptionPane.ERROR_MESSAGE);
    }
}
