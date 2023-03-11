package ru.itis.graduationwork.application.managers.content;

import com.google.gson.Gson;
import ru.itis.graduationwork.application.entities.ProjectConfig;
import ru.itis.graduationwork.application.entities.RecentList;
import ru.itis.graduationwork.application.entities.RecentListEntry;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.properties.RecentListReadingException;
import ru.itis.graduationwork.exceptions.properties.RecentListWritingException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class RecentManager {

    public static final String RECENT_PROJECTS_FILE_PATH = "settings/recent-projects.json";
    public static final String RECENT_TASKS_FILE_PATH = "settings/recent-tasks.json";

    public static RecentList getRecentProjects(){
        try {
            return getRecentList(RECENT_PROJECTS_FILE_PATH);
        } catch (RecentListReadingException exception){
            ExceptionsManager.addDelayedException(
                    ExceptionsManager::handleRecentListReadingException, 200, TimeUnit.MILLISECONDS
            );
            return new RecentList();
        }
    }

    public static RecentList getRecentTasks(){
        try{
            return getRecentList(RECENT_TASKS_FILE_PATH);
        } catch (RecentListReadingException exception){
            ExceptionsManager.addDelayedException(
                    ExceptionsManager::handleRecentListReadingException, 200, TimeUnit.MILLISECONDS
            );
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
            ExceptionsManager.addDelayedException(
                    ExceptionsManager::handleRecentListWritingException, 200, TimeUnit.MILLISECONDS
            );
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
            ExceptionsManager.addDelayedException(
                    ExceptionsManager::handleRecentListWritingException, 200, TimeUnit.MILLISECONDS
            );
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
            ExceptionsManager.addDelayedException(
                    ExceptionsManager::handleRecentListWritingException, 200, TimeUnit.MILLISECONDS
            );
        }
    }

}
