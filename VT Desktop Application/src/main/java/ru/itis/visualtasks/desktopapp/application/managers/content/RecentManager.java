package ru.itis.visualtasks.desktopapp.application.managers.content;

import com.google.gson.Gson;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.entities.project.RecentList;
import ru.itis.visualtasks.desktopapp.application.entities.project.RecentListEntry;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.exceptions.properties.RecentListReadingException;
import ru.itis.visualtasks.desktopapp.exceptions.properties.RecentListWritingException;
import ru.itis.visualtasks.desktopapp.utils.PropertiesUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class RecentManager {

    public static final String RECENT_PROJECTS_FILE_PATH;
    public static final String RECENT_TASKS_FILE_PATH;

    static {
        RECENT_PROJECTS_FILE_PATH = PropertiesUtils.getInstance().getProperties().getProperty("settings-path")
                + File.separator + "recent-projects.json";
        if (!FilesManager.checkIsFileExist(RECENT_PROJECTS_FILE_PATH)){
           saveRecentProjects(new RecentList());
        }
        RECENT_TASKS_FILE_PATH = PropertiesUtils.getInstance().getProperties().getProperty("settings-path")
                + File.separator + "recent-tasks.json";
        if (!FilesManager.checkIsFileExist(RECENT_TASKS_FILE_PATH)){
            saveRecentTasks(new RecentList());
        }
    }

    public static RecentList getRecentProjects(){
        try {
            return getRecentList(RECENT_PROJECTS_FILE_PATH);
        } catch (RecentListReadingException exception){
            exception.handle();
            return new RecentList();
        }
    }

    public static RecentList getRecentTasks(){
        try{
            return getRecentList(RECENT_TASKS_FILE_PATH);
        } catch (RecentListReadingException exception){
            exception.handle();
            return new RecentList();
        }
    }

    private static RecentList getRecentList(String path){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path,
                StandardCharsets.UTF_8))){
            RecentList recentList = new Gson().fromJson(bufferedReader, RecentList.class);
            return recentList == null ? new RecentList() : recentList;
        } catch (IOException ex) {
            throw new RecentListReadingException(ex);
        }
    }

    public static void addRecentProject(ProjectConfig projectConfig){
        RecentList recentList = getRecentProjects();
        recentList.getContent().put(
                projectConfig.getProjectPath(),
                new RecentListEntry(projectConfig.getProjectName(), System.currentTimeMillis())
        );
        try{
            saveRecentProjects(recentList);
        } catch (RecentListWritingException exception){
            exception.handle();
        }
    }

    public static void saveRecentProjects(RecentList recentList){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(RECENT_PROJECTS_FILE_PATH,
                StandardCharsets.UTF_8))){
            bufferedWriter.write(new Gson().toJson(recentList));
        } catch (IOException ex) {
            throw new RecentListWritingException(ex);
        }
    }

    public static void addRecentTask(ProjectConfig projectConfig){
        RecentList recentList = getRecentTasks();
        recentList.getContent().put(
                projectConfig.getProjectPath(),
                new RecentListEntry(projectConfig.getProjectName(), System.currentTimeMillis())
        );
        try{
            saveRecentTasks(recentList);
        } catch (RecentListWritingException exception){
            exception.handle();
        }
    }

    public static void saveRecentTasks(RecentList recentList){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(RECENT_TASKS_FILE_PATH,
                StandardCharsets.UTF_8))){
            bufferedWriter.write(new Gson().toJson(recentList));
        } catch (IOException ex) {
            throw new RecentListWritingException(ex);
        }
    }

    public static void deleteRecentProject(String recentProjectPath){
        RecentList recentList = getRecentProjects();
        recentList.getContent().remove(recentProjectPath);
        try{
            saveRecentProjects(recentList);
        } catch (RecentListWritingException exception){
            exception.handle();
        }
    }

    public static void deleteRecentTask(String recentTaskPath){
        RecentList recentList = getRecentTasks();
        recentList.getContent().remove(recentTaskPath);
        try{
            saveRecentTasks(recentList);
        } catch (RecentListWritingException exception){
            exception.handle();
        }
    }

}
