package ru.itis.visualtasks.desktopapp.application.managers.project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationType;
import ru.itis.visualtasks.desktopapp.application.generators.FilesGenerator;
import ru.itis.visualtasks.desktopapp.application.generators.JavaVisualizationFilesGenerator;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileGenerationException;
import ru.itis.visualtasks.desktopapp.exceptions.project.ProjectConfigReadingException;
import ru.itis.visualtasks.desktopapp.exceptions.project.ProjectConfigWritingException;
import ru.itis.visualtasks.desktopapp.exceptions.project.ProjectCreationException;
import ru.itis.visualtasks.desktopapp.exceptions.project.ProjectDirectoryNotExistsException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class ProjectFilesManager {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String VISUALIZATION_FOLDER_NAME = "visualization";
    private static final String FILE_WRAPPERS_FOLDER_NAME = "wrappers";
    private static final String INFORMATIONAL_FOLDER_NAME = "info";
    private static final String CUSTOM_PANEL_FILE_NAME = "CustomPanel.java";
    private static final String VISUALIZATION_SCENE_PANEL_FILE_NAME = "VisualizationScenePanel.java";

    private static ProjectConfig projectConfig;
    private static FilesGenerator filesGenerator;

    public static void init(ProjectConfig projectConfig){
        ProjectFilesManager.projectConfig = projectConfig;
        filesGenerator = FilesGeneratorManager.getFilesGenerator(projectConfig.getLanguage());
    }

    public static void reset(){
        projectConfig = null;
        filesGenerator = null;
    }

    public static String getSolutionFileName(){
        return filesGenerator.getSolutionFileName();
    }

    public static String getTestSolutionFileName(){
        return filesGenerator.getTestSolutionFileName();
    }

    public static void createProjectFolders(String path) {
        try{
            Files.createDirectories(Paths.get(path));
        } catch (IOException ex) {
            throw new ProjectCreationException(ex);
        }
    }

    public static void createProjectFiles(){
        try {
            createVisualizationFolderIfNotExists();
            createWrappersFilesFolderIfNotExists();
            createInformationalFilesFolderIfNotExists();
            createSolutionFileIfNotExists();
            createTestSolutionFileIfNotExists();
            createCustomPanelFileIfNotExists();
            createVisualizationScenePanelFileIfNotExists();
            createAdditionalFiles();
        } catch (FileGenerationException exception){
            exception.handle();
        }
    }

    public static void createVisualizationFiles(){
        createVisualizationFolderIfNotExists();
        createCustomPanelFileIfNotExists();
        createVisualizationScenePanelFileIfNotExists();
    }


    private static void createVisualizationFolderIfNotExists() {
        if (checkIfVisualizationFolderNotExists()){
            createVisualizationFolder();
            createVisualizationReadMeFile();
        }
    }

    private static boolean checkIfVisualizationFolderNotExists(){
        String visualizationFolderPath = getVisualizationFolderPath();
        return !FilesManager.checkIsFileExist(visualizationFolderPath);
    }

    private static String getVisualizationFolderPath(){
        return projectConfig.getProjectPath() + File.separator + VISUALIZATION_FOLDER_NAME;
    }

    private static void createVisualizationFolder() {
        try{
            Files.createDirectories(Paths.get(getVisualizationFolderPath()));
        } catch (IOException ex) {
            throw new ProjectCreationException(ex);
        }
    }

    private static void createVisualizationReadMeFile(){
        filesGenerator.generateVisualizationReadMeFile();
    }


    private static void createWrappersFilesFolderIfNotExists() {
        if (checkIfWrappersFolderNotExists()){
            createWrappersFilesFolder();
            createWrappersReadMeFile();
        }
    }

    private static boolean checkIfWrappersFolderNotExists() {
        String wrappersFolderPath = getWrappersFolderPath();
        return !FilesManager.checkIsFileExist(wrappersFolderPath);
    }

    private static String getWrappersFolderPath(){
        return projectConfig.getProjectPath() + File.separator + FILE_WRAPPERS_FOLDER_NAME;
    }

    private static void createWrappersFilesFolder() {
        try{
            Files.createDirectories(Paths.get(getWrappersFolderPath()));
        } catch (IOException ex) {
            throw new ProjectCreationException(ex);
        }
    }

    private static void createWrappersReadMeFile(){
        filesGenerator.generateWrappersReadMeFile();
    }


    private static void createInformationalFilesFolderIfNotExists(){
        if (checkIfInformationalFolderNotExists()){
            createInformationalFolder();
            createInformationalReadMeFile();
        }
    }

    private static boolean checkIfInformationalFolderNotExists() {
        String informationalFolderPath = getInformationalFolderPath();
        return !FilesManager.checkIsFileExist(informationalFolderPath);
    }

    private static String getInformationalFolderPath(){
        return projectConfig.getProjectPath() + File.separator + INFORMATIONAL_FOLDER_NAME;
    }

    private static void createInformationalFolder() {
        try{
            Files.createDirectories(Paths.get(getInformationalFolderPath()));
        } catch (IOException ex) {
            throw new ProjectCreationException(ex);
        }
    }

    private static void createInformationalReadMeFile() {
        filesGenerator.generateInformationalReadMeFile();
    }


    private static void createSolutionFileIfNotExists(){
        if (checkIfSolutionFileNotExists()){
            createSolutionFile();
        }
    }

    private static boolean checkIfSolutionFileNotExists() {
        String solutionFilePath = getSolutionFilePath();
        return !FilesManager.checkIsFileExist(solutionFilePath);
    }

    public static String getSolutionFilePath(){
        return projectConfig.getProjectPath() + File.separator + filesGenerator.getSolutionFileName();
    }

    private static void createSolutionFile(){
        filesGenerator.generateSolutionFile();
    }


    private static void createTestSolutionFileIfNotExists(){
        if (checkIfTestSolutionFileNotExists()){
            createTestSolutionFile();
        }
    }

    private static boolean checkIfTestSolutionFileNotExists() {
        String testSolutionFilePath = getTestSolutionFilePath();
        return !FilesManager.checkIsFileExist(testSolutionFilePath);
    }

    public static String getTestSolutionFilePath(){
        return projectConfig.getProjectPath() + File.separator + filesGenerator.getTestSolutionFileName();
    }

    private static void createTestSolutionFile() {
        filesGenerator.generateTestSolutionFile();
    }


    private static void createCustomPanelFileIfNotExists() {
        if (checkIfCustomPanelFileNotExists()){
            createCustomPanelFile();
        }
    }

    private static boolean checkIfCustomPanelFileNotExists() {
        String customPanelFilePath = getCustomPanelFilePath();
        return !FilesManager.checkIsFileExist(customPanelFilePath);
    }

    private static String getCustomPanelFilePath(){
        String visualizationFolderPath = getVisualizationFolderPath();
        return visualizationFolderPath + File.separator + CUSTOM_PANEL_FILE_NAME;
    }

    private static void createCustomPanelFile() {
        JavaVisualizationFilesGenerator.generateCustomPanelFile();
    }


    private static void createVisualizationScenePanelFileIfNotExists() {
        if (checkIfVisualizationScenePanelFileNotExists()){
            createVisualizationScenePanelFile();
        }
    }

    private static boolean checkIfVisualizationScenePanelFileNotExists() {
        String visualizationScenePanelFilePath = getVisualizationScenePanelFilePath();
        return !FilesManager.checkIsFileExist(visualizationScenePanelFilePath);
    }

    private static String getVisualizationScenePanelFilePath(){
        String visualizationFolderPath = getVisualizationFolderPath();
        return visualizationFolderPath + File.separator + VISUALIZATION_SCENE_PANEL_FILE_NAME;
    }

    private static void createVisualizationScenePanelFile() {
        JavaVisualizationFilesGenerator.generateVisualizationScenePanelFile();
    }


    private static void createAdditionalFiles() {
        filesGenerator.generateAdditionalFiles();
    }


    private static String getConfigPath(String projectPath){
        return projectPath + File.separator + "config.json";
    }

    public static ProjectConfig getConfigFile(String projectPath){
        if (!isProjectExists(projectPath)){
            throw new ProjectDirectoryNotExistsException(projectPath);
        }
        ProjectConfig projectConfig;
        try {
            projectConfig = readConfigFile(projectPath);
            projectConfig.setProjectPath(projectPath);
            return projectConfig;
        } catch (ProjectConfigReadingException exception){
            exception.handle();
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
                .visualizationType(VisualizationType.SWING)
                .wrappersNames(new HashMap<>())
                .build();
    }

}
