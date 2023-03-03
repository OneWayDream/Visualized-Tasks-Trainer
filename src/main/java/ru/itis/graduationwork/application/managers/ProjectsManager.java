package ru.itis.graduationwork.application.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.entities.NewProjectInfo;
import ru.itis.graduationwork.application.entities.ProjectConfig;
import ru.itis.graduationwork.application.settings.Mode;
import ru.itis.graduationwork.application.ui.core.PageType;
import ru.itis.graduationwork.application.ui.core.explorer.FileWatchService;
import ru.itis.graduationwork.application.ui.core.ide.IdePageFrame;
import ru.itis.graduationwork.exceptions.ProjectCreationException;
import ru.itis.graduationwork.exceptions.project.ProjectDirectoryNotExistsException;
import ru.itis.graduationwork.exceptions.project.ProjectConfigReadingException;
import ru.itis.graduationwork.exceptions.project.ProjectConfigWritingException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class ProjectsManager {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void createProject(NewProjectInfo newProjectInfo) {
        createFolders(newProjectInfo.getProjectPath());
        createConfigFile(newProjectInfo);
    }

    private static void createFolders(String path) {
        try{
            Files.createDirectories(Paths.get(path));
        } catch (IOException ex) {
            throw new ProjectCreationException(ex);
        }
    }

    private static void createConfigFile(NewProjectInfo newProjectInfo) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(getConfigPath(newProjectInfo.getProjectPath()),
                StandardCharsets.UTF_8))){
            ProjectConfig projectConfig = ProjectConfig.builder()
                    .projectName(newProjectInfo.getProjectName())
                    .language(newProjectInfo.getLanguage())
                    .build();
            bufferedWriter.write(gson.toJson(projectConfig));
        } catch (IOException ex) {
            throw new ProjectCreationException(ex);
        }
    }

    private static String getConfigPath(String projectPath){
        return projectPath + File.separator + "config.json";
    }

    public static void openProject(String projectPath){
        ProjectConfig projectConfig;
        try{
            projectConfig = getConfigFile(projectPath);
        } catch (ProjectConfigReadingException exception){
            ExceptionsManager.addDelayedException(
                    ExceptionsManager::handleProjectConfigReadingException, 200, TimeUnit.MILLISECONDS
            );
            projectConfig = getDefaultProjectConfig(projectPath);
            writeConfigFile(projectConfig);
        }
        IdePageFrame pageFrame = getIdePageFrame(projectConfig);
        Application.changePage(pageFrame);
        FileWatchService.clearMonitoringTreePaths();
    }

    private static ProjectConfig getConfigFile(String projectPath){
        if (!isProjectExists(projectPath)){
            throw new ProjectDirectoryNotExistsException();
        }
        ProjectConfig projectConfig = readConfigFile(projectPath);
        projectConfig.setProjectPath(projectPath);
        return projectConfig;
    }

    private static boolean isProjectExists(String projectPath){
        return new File(projectPath).exists();
    }

    private static IdePageFrame getIdePageFrame(ProjectConfig projectConfig){
        IdePageFrame pageFrame;
        if (Application.getMode().equals(Mode.DEVELOP)){
            pageFrame = (IdePageFrame) PagesManager.getPage(PageType.DEVELOP);
            RecentManager.addRecentProject(projectConfig);
        } else {
            pageFrame = (IdePageFrame) PagesManager.getPage(PageType.STUDY);
            RecentManager.addRecentTask(projectConfig);
        }
        pageFrame.setProjectConfig(projectConfig);
        ConfigManager.setConfig(projectConfig);
        return pageFrame;
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

    public static ProjectConfig getDefaultProjectConfig(String projectPath){
        return ProjectConfig.builder()
                .projectPath(projectPath)
                .language("java")
                .projectName(projectPath)
                .build();
    }

}
