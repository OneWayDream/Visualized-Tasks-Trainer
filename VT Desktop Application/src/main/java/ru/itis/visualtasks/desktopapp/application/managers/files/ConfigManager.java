package ru.itis.visualtasks.desktopapp.application.managers.files;

import lombok.Getter;
import lombok.Setter;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationType;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.exceptions.project.ProjectConfigNotInitialisedException;
import ru.itis.visualtasks.desktopapp.exceptions.project.ProjectConfigWritingException;

import java.io.File;
import java.util.Map;

public class ConfigManager {

    @Setter
    @Getter
    private static ProjectConfig config;

    private static boolean isConfigInitialised(){
        return config != null;
    }

    public static String getProjectName(){
        if (isConfigInitialised()){
            return config.getProjectName();
        } else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static String getGeneralDescriptionFilePath(){
        if (isConfigInitialised()){
            return (config.getGeneralDescriptionFilePath() == null) ? null :
                    config.getProjectPath() + File.separator + config.getGeneralDescriptionFilePath();
        } else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static void setGeneralDescriptionFilePath(String generalDescriptionFilePath){
        if (isConfigInitialised()){
            if (generalDescriptionFilePath != null){
                generalDescriptionFilePath = generalDescriptionFilePath.substring(
                        config.getProjectPath().length() + 1);
            }
            config.setGeneralDescriptionFilePath(generalDescriptionFilePath);
            try{
                ProjectFilesManager.writeConfigFile(config);
            } catch (ProjectConfigWritingException exception){
                exception.handle();
            }
        } else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static String getStudyingContentFilePath(){
        if (isConfigInitialised()){
            return (config.getStudyingContentFilePath() == null) ? null :
                    config.getProjectPath() + File.separator + config.getStudyingContentFilePath();
        } else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static void setStudyingContentFilePath(String studyingContentFilePath){
        if (isConfigInitialised()){
            if (studyingContentFilePath != null){
                studyingContentFilePath = studyingContentFilePath.substring(
                        config.getProjectPath().length() + 1);
            }
            config.setStudyingContentFilePath(studyingContentFilePath);
            try{
                ProjectFilesManager.writeConfigFile(config);
            } catch (ProjectConfigWritingException exception){
                exception.handle();
            }
        } else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static String getTaskTermsFilePath(){
        if (isConfigInitialised()){
            return (config.getTaskTermsFilePath() == null) ? null :
                    config.getProjectPath() + File.separator + config.getTaskTermsFilePath();}
        else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static void setTaskTermsFilePath(String taskTermsFilePath){
        if (isConfigInitialised()){
            if (taskTermsFilePath != null){
                taskTermsFilePath = taskTermsFilePath.substring(
                        config.getProjectPath().length() + 1);
            }
            config.setTaskTermsFilePath(taskTermsFilePath);
            try{
                ProjectFilesManager.writeConfigFile(config);
            } catch (ProjectConfigWritingException exception){
                exception.handle();
            }
        } else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static String getProjectPath(){
        if (isConfigInitialised()){
            return config.getProjectPath();}
        else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static Language getProjectLanguage(){
        if (isConfigInitialised()){
            return config.getLanguage();}
        else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static VisualizationType getProjectVisualizationType(){
        if (isConfigInitialised()){
            return config.getVisualizationType();}
        else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static Map<String, String> getWrappersNames(){
        if (isConfigInitialised()){
            return config.getWrappersNames();}
        else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static void setWrappersNames(Map<String, String> wrappersNames){
        if (isConfigInitialised()){
            config.setWrappersNames(wrappersNames);
            try{
                ProjectFilesManager.writeConfigFile(config);
            } catch (ProjectConfigWritingException exception){
                exception.handle();
            }
        } else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static void saveConfigFile(){
        ProjectFilesManager.writeConfigFile(config);
    }

}
