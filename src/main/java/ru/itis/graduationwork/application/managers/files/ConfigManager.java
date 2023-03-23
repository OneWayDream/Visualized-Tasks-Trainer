package ru.itis.graduationwork.application.managers.files;

import lombok.Getter;
import lombok.Setter;
import ru.itis.graduationwork.application.entities.project.Language;
import ru.itis.graduationwork.application.entities.project.ProjectConfig;
import ru.itis.graduationwork.application.entities.project.VisualizationType;
import ru.itis.graduationwork.application.managers.project.ProjectFilesManager;
import ru.itis.graduationwork.exceptions.project.ProjectConfigNotInitialisedException;
import ru.itis.graduationwork.exceptions.project.ProjectConfigWritingException;

import java.util.Map;

public class ConfigManager {

    @Setter
    @Getter
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
            return config.getStudyingContentFilePath();
        } else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static void setStudyingContentFilePath(String studyingContentFilePath){
        if (isConfigInitialised()){
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
            return config.getTaskTermsFilePath();}
        else {
            throw new ProjectConfigNotInitialisedException();
        }
    }

    public static void setTaskTermsFilePath(String taskTermsFilePath){
        if (isConfigInitialised()){
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

}