package ru.itis.graduationwork.application.managers;

import lombok.Setter;
import ru.itis.graduationwork.application.entities.ProjectConfig;
import ru.itis.graduationwork.exceptions.project.ProjectConfigNotInitialisedException;
import ru.itis.graduationwork.exceptions.project.ProjectConfigWritingException;

public class ConfigManager {

    @Setter
    private static ProjectConfig config;

    private static boolean isConfigInitialised(){
        return config != null;
    }

    public static String getGeneralDescriptionFilePath(){
        if (isConfigInitialised()){
            return config.getGeneralDescriptionFilePath();
        } else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static void setGeneralDescriptionFilePath(String generalDescriptionFilePath){
        if (isConfigInitialised()){
            config.setGeneralDescriptionFilePath(generalDescriptionFilePath);
            try{
                ProjectsManager.writeConfigFile(config);
            } catch (ProjectConfigWritingException exception){
                ExceptionsManager.handleProjectConfigWritingException();
            }
        } else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static String getStudyingContentFilePath(){
        if (isConfigInitialised()){
            return config.getStudyingContentFilePath();
        } else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static void setStudyingContentFilePath(String studyingContentFilePath){
        if (isConfigInitialised()){
            config.setStudyingContentFilePath(studyingContentFilePath);
            try{
                ProjectsManager.writeConfigFile(config);
            } catch (ProjectConfigWritingException exception){
                ExceptionsManager.handleProjectConfigWritingException();
            }
        } else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static String getTaskTermsFilePath(){
        if (isConfigInitialised()){
            return config.getTaskTermsFilePath();}
        else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static void setTaskTermsFilePath(String taskTermsFilePath){
        if (isConfigInitialised()){
            config.setTaskTermsFilePath(taskTermsFilePath);
            try{
                ProjectsManager.writeConfigFile(config);
            } catch (ProjectConfigWritingException exception){
                ExceptionsManager.handleProjectConfigWritingException();
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

}
