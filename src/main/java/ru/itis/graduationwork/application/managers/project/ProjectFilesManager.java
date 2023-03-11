package ru.itis.graduationwork.application.managers.project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.itis.graduationwork.application.entities.Language;
import ru.itis.graduationwork.application.entities.ProjectConfig;
import ru.itis.graduationwork.application.loaders.TemplatesLoader;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.files.FilesManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.ProjectCreationException;
import ru.itis.graduationwork.exceptions.project.ProjectConfigReadingException;
import ru.itis.graduationwork.exceptions.project.ProjectConfigWritingException;
import ru.itis.graduationwork.exceptions.project.ProjectDirectoryNotExistsException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class ProjectFilesManager {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static final String SOLUTION_FILE_NAME = "Solution";
    private static final String TEST_SOLUTION_FILE_NAME = "TestSolution";
    private static final String VISUALIZATION_FOLDER_NAME = "visualization";
    private static final String FILE_WRAPPERS_FOLDER_NAME = "wrappers";
    private static final String INFORMATIONAL_FOLDER_NAME = "info";
    private static final String READ_ME_FILE_NAME = "readme.md";
    private static final String CUSTOM_PANEL_FILE_NAME = "CustomPanel";
    private static final String VISUALIZATION_SCENE_PANEL_FILE_NAME = "VisualizationScenePanel";

    public static void createProjectFolders(String path) {
        try{
            Files.createDirectories(Paths.get(path));
        } catch (IOException ex) {
            throw new ProjectCreationException(ex);
        }
    }

    public static void createProjectFiles(ProjectConfig projectConfig){
        createConfigFile(projectConfig);
        createDevelopProjectFiles(projectConfig);
    }

    public static void createDevelopProjectFiles(ProjectConfig projectConfig){
        createSolutionFileIfNotExists(projectConfig);
        createTestSolutionFileIfNotExists(projectConfig);
        createVisualizationFolderIfNotExists(projectConfig);
        createWrappersFilesFolderIfNotExists(projectConfig);
        createInformationalFilesFolderIfNotExists(projectConfig);
        createCustomPanelFileIfNotExists(projectConfig);
        createVisualizationScenePanelFileIfNotExists(projectConfig);
    }

    private static void createVisualizationFolderIfNotExists(ProjectConfig projectConfig) {
        createVisualizationFolder(projectConfig.getProjectPath());
        createVisualizationReadMeIfNotExists(projectConfig.getProjectPath());
    }

    private static void createVisualizationFolder(String projectPath) {
        try{
            Files.createDirectories(Paths.get(getVisualizationFolderPath(projectPath)));
        } catch (IOException ex) {
            throw new ProjectCreationException(ex);
        }
    }

    private static String getVisualizationFolderPath(String projectPath){
        return projectPath + File.separator + VISUALIZATION_FOLDER_NAME;
    }

    private static String getVisualizationReadMeFilePath(String projectPath){
        String visualizationFolderPath = getVisualizationFolderPath(projectPath);
        return visualizationFolderPath + File.separator + READ_ME_FILE_NAME;
    }

    private static void createVisualizationReadMeIfNotExists(String projectPath) {
        String visualizationReadMeFilePath = getVisualizationReadMeFilePath(projectPath);
        if (!FilesManager.checkIsFileExist(visualizationReadMeFilePath)){
            createVisualizationReadMeFile(visualizationReadMeFilePath);
        }
    }

    private static void createVisualizationReadMeFile(String visualizationReadMeFilePath){
        String visualizationReadMeFileContent = prepareVisualizationReadMeFileContent();
        FilesManager.writeStringAsFile(visualizationReadMeFilePath, visualizationReadMeFileContent);
    }

    private static String prepareVisualizationReadMeFileContent(){
        return LocalizationManager.getLocaleTextByKey("templates.visualization-read-me-file.content");
    }

    private static void createWrappersFilesFolderIfNotExists(ProjectConfig projectConfig) {
        createWrappersFilesFolder(projectConfig.getProjectPath());
        createWrappersReadMeIfNotExists(projectConfig.getProjectPath());
    }

    private static void createWrappersFilesFolder(String projectPath) {
        try{
            Files.createDirectories(Paths.get(getWrappersFolderPath(projectPath)));
        } catch (IOException ex) {
            throw new ProjectCreationException(ex);
        }
    }

    private static String getWrappersFolderPath(String projectPath){
        return projectPath + File.separator + FILE_WRAPPERS_FOLDER_NAME;
    }

    private static String getWrappersReadMeFilePath(String projectPath){
        String wrappersFolderPath = getWrappersFolderPath(projectPath);
        return wrappersFolderPath + File.separator + READ_ME_FILE_NAME;
    }

    private static void createWrappersReadMeIfNotExists(String projectPath) {
        String wrappersReadMeFilePath = getWrappersReadMeFilePath(projectPath);
        if (!FilesManager.checkIsFileExist(wrappersReadMeFilePath)){
            createWrappersReadMeFile(wrappersReadMeFilePath);
        }
    }

    private static void createWrappersReadMeFile(String wrappersReadMeFilePath){
        String wrappersReadMeFileContent = prepareWrappersReadMeFileContent();
        FilesManager.writeStringAsFile(wrappersReadMeFilePath, wrappersReadMeFileContent);
    }

    private static String prepareWrappersReadMeFileContent(){
        return LocalizationManager.getLocaleTextByKey("templates.wrappers-read-me-file.content");
    }

    private static void createInformationalFilesFolderIfNotExists(ProjectConfig projectConfig){
        createInformationalFolder(projectConfig.getProjectPath());
        createInformationalReadMeIfNotExists(projectConfig.getProjectPath());
    }

    private static void createInformationalFolder(String projectPath) {
        try{
            Files.createDirectories(Paths.get(getInformationalFolderPath(projectPath)));
        } catch (IOException ex) {
            throw new ProjectCreationException(ex);
        }
    }

    private static String getInformationalFolderPath(String projectPath){
        return projectPath + File.separator + INFORMATIONAL_FOLDER_NAME;
    }

    private static String getInformationalReadMeFilePath(String projectPath){
        String informationalFolderPath = getInformationalFolderPath(projectPath);
        return informationalFolderPath + File.separator + READ_ME_FILE_NAME;
    }

    private static void createInformationalReadMeIfNotExists(String projectPath) {
        String informationalReadMeFilePath = getInformationalReadMeFilePath(projectPath);
        if (!FilesManager.checkIsFileExist(informationalReadMeFilePath)){
            createInformationalReadMeFile(informationalReadMeFilePath);
        }
    }

    private static void createInformationalReadMeFile(String informationalReadMeFilePath) {
        String informationalReadMeFileContent = prepareInformationalReadMeFileContent();
        FilesManager.writeStringAsFile(informationalReadMeFilePath, informationalReadMeFileContent);
    }

    private static String prepareInformationalReadMeFileContent() {
        return LocalizationManager.getLocaleTextByKey("templates.informational-read-me-file.content");
    }

    private static void createCustomPanelFileIfNotExists(ProjectConfig projectConfig) {
        String customPanelFilePath = getCustomPanelFilePath(projectConfig.getProjectPath(), projectConfig.getLanguage());
        if (!FilesManager.checkIsFileExist(customPanelFilePath)){
            createCustomPanelFile(projectConfig);
        }
    }

    private static String getCustomPanelFilePath(String projectPath, Language language){
        String visualizationFolderPath = getVisualizationFolderPath(projectPath);
        return visualizationFolderPath + File.separator + CUSTOM_PANEL_FILE_NAME + language.getExtension();
    }

    private static void createCustomPanelFile(ProjectConfig projectConfig) {
        String customPanelFilePath = getCustomPanelFilePath(projectConfig.getProjectPath(), projectConfig.getLanguage());
        String customPanelFileContent = prepareCustomPanelFileContent();
        FilesManager.writeStringAsFile(customPanelFilePath, customPanelFileContent);
    }

    private static String prepareCustomPanelFileContent() {
        String customPanelContent = TemplatesLoader.getCustomPanelTemplateContent();
        return String.format(customPanelContent,
                LocalizationManager.getLocaleTextByKey("templates.custom-panel-file-template.description"));
    }

    private static void createVisualizationScenePanelFileIfNotExists(ProjectConfig projectConfig) {
        String customPanelFilePath = getVisualizationScenePanelFilePath(projectConfig.getProjectPath(), projectConfig.getLanguage());
        if (!FilesManager.checkIsFileExist(customPanelFilePath)){
            createVisualizationScenePanelFile(projectConfig);
        }
    }

    private static String getVisualizationScenePanelFilePath(String projectPath, Language language){
        String visualizationFolderPath = getVisualizationFolderPath(projectPath);
        return visualizationFolderPath + File.separator + VISUALIZATION_SCENE_PANEL_FILE_NAME + language.getExtension();
    }

    private static void createVisualizationScenePanelFile(ProjectConfig projectConfig) {
        String visualizationScenePanelFilePath = getVisualizationScenePanelFilePath(projectConfig.getProjectPath(), projectConfig.getLanguage());
        String visualizationScenePanelFileContent = prepareVisualizationScenePanelFileContent();
        FilesManager.writeStringAsFile(visualizationScenePanelFilePath, visualizationScenePanelFileContent);
    }

    private static String prepareVisualizationScenePanelFileContent() {
        return TemplatesLoader.getVisualizationScenePanelTemplateContent();
    }

    private static void createConfigFile(ProjectConfig projectConfig) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(getConfigPath(projectConfig.getProjectPath()),
                        StandardCharsets.UTF_8))){
            bufferedWriter.write(gson.toJson(projectConfig));
        } catch (IOException ex) {
            throw new ProjectCreationException(ex);
        }
    }

    private static String getConfigPath(String projectPath){
        return projectPath + File.separator + "config.json";
    }

    private static void createSolutionFileIfNotExists(ProjectConfig projectConfig){
        String solutionFilePath = ProjectFilesManager.getSolutionFilePath(
                projectConfig.getProjectPath(), projectConfig.getLanguage());
        if (!FilesManager.checkIsFileExist(solutionFilePath)){
            createSolutionFile(projectConfig);
        }
    }

    private static void createSolutionFile(ProjectConfig projectConfig){
        String solutionFilePath = getSolutionFilePath(projectConfig.getProjectPath(), projectConfig.getLanguage());
        String solutionFileContent = prepareSolutionFileContent(projectConfig.getLanguage());
        FilesManager.writeStringAsFile(solutionFilePath, solutionFileContent);
    }

    private static String prepareSolutionFileContent(Language language){
        String solutionFileContent = TemplatesLoader.getSolutionTemplateContent(language);
        return String.format(solutionFileContent,
                LocalizationManager.getLocaleTextByKey("templates.solution-file-template.description"));
    }

    private static void createTestSolutionFileIfNotExists(ProjectConfig projectConfig){
        String solutionFilePath = ProjectFilesManager.getTestSolutionFilePath(
                projectConfig.getProjectPath(), projectConfig.getLanguage());
        if (!FilesManager.checkIsFileExist(solutionFilePath)){
            createTestSolutionFile(projectConfig);
        }
    }

    private static void createTestSolutionFile(ProjectConfig projectConfig) {
        String testSolutionFilePath = getTestSolutionFilePath(projectConfig.getProjectPath(), projectConfig.getLanguage());
        String testSolutionFileContent = prepareTestSolutionFileContent(projectConfig.getLanguage());
        FilesManager.writeStringAsFile(testSolutionFilePath, testSolutionFileContent);
    }

    private static String prepareTestSolutionFileContent(Language language) {
        String solutionFileContent = TemplatesLoader.getTestSolutionTemplateContent(language);
        return String.format(solutionFileContent,
                LocalizationManager.getLocaleTextByKey("templates.test-solution-file-template.description"));
    }

    public static ProjectConfig getConfigFile(String projectPath){
        if (!isProjectExists(projectPath)){
            throw new ProjectDirectoryNotExistsException();
        }
        ProjectConfig projectConfig;
        try {
            projectConfig = readConfigFile(projectPath);
            projectConfig.setProjectPath(projectPath);
            return projectConfig;
        } catch (ProjectConfigReadingException exception){
            ExceptionsManager.addDelayedException(
                    ExceptionsManager::handleProjectConfigReadingException, 200, TimeUnit.MILLISECONDS
            );
            projectConfig = getDefaultProjectConfig(projectPath);
            writeConfigFile(projectConfig);
        }
        return projectConfig;
    }

    private static ProjectConfig readConfigFile(String projectPath){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getConfigPath(projectPath),
                StandardCharsets.UTF_8))){
            return gson.fromJson(bufferedReader, ProjectConfig.class);
        } catch (IOException ex) {
            throw new ProjectConfigReadingException(ex);
        }
    }

    public static void writeConfigFile(ProjectConfig config){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getConfigPath(config.getProjectPath()),
                StandardCharsets.UTF_8))){
            bufferedWriter.write(gson.toJson(config));
        } catch (IOException ex) {
            throw new ProjectConfigWritingException(ex);
        }
    }

    private static boolean isProjectExists(String projectPath){
        return FilesManager.checkIsFileExist(projectPath);
    }

    private static ProjectConfig getDefaultProjectConfig(String projectPath){
        return ProjectConfig.builder()
                .projectPath(projectPath)
                .language(Language.JAVA)
                .projectName(projectPath)
                .build();
    }

    public static String getSolutionFilePath(String projectPath, Language language){
        return projectPath + File.separator + SOLUTION_FILE_NAME + language.getExtension();
    }

    public static String getTestSolutionFilePath(String projectPath, Language language){
        return projectPath + File.separator + TEST_SOLUTION_FILE_NAME + language.getExtension();
    }

    public static String getSolutionFilePath(){
        return ConfigManager.getProjectPath() + File.separator + SOLUTION_FILE_NAME +
                ConfigManager.getProjectLanguage().getExtension();
    }

    public static String getTestSolutionFilePath(){
        return ConfigManager.getProjectPath() + File.separator + TEST_SOLUTION_FILE_NAME
                + ConfigManager.getProjectLanguage().getExtension();
    }

}
