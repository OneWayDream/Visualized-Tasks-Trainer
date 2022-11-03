package ru.itis.graduationwork.application.managers;

import com.google.gson.Gson;
import ru.itis.graduationwork.application.settings.entities.RecentList;
import ru.itis.graduationwork.exceptions.application.JsonReadException;
import ru.itis.graduationwork.exceptions.application.JsonWriteException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class RecentManager {

    public static final String RECENT_PROJECTS_FILE_PATH = "settings/recent-projects.json";
    public static final String RECENT_TASKS_FILE_PATH = "settings/recent-tasks.json";

    public static RecentList getRecentProjects(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(RECENT_PROJECTS_FILE_PATH,
                StandardCharsets.UTF_8))){
            return new Gson().fromJson(bufferedReader, RecentList.class);
        } catch (IOException ex) {
            throw new JsonReadException(ex);
        }
    }

    public static RecentList getRecentTasks(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(RECENT_TASKS_FILE_PATH,
                StandardCharsets.UTF_8))){
            return new Gson().fromJson(bufferedReader, RecentList.class);
        } catch (IOException ex) {
            throw new JsonReadException(ex);
        }
    }

    public static void saveRecentProjects(RecentList recentList){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(RECENT_PROJECTS_FILE_PATH,
                StandardCharsets.UTF_8))){
            bufferedWriter.write(new Gson().toJson(recentList));
        } catch (IOException ex) {
            throw new JsonWriteException(ex);
        }
    }

    public static void saveRecentTasks(RecentList recentList){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(RECENT_TASKS_FILE_PATH,
                StandardCharsets.UTF_8))){
            bufferedWriter.write(new Gson().toJson(recentList));
        } catch (IOException ex) {
            throw new JsonWriteException(ex);
        }
    }

}
