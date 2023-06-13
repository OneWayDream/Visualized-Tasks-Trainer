package ru.itis.visualtasks.tasksserver.utils;

import com.google.gson.Gson;
import ru.itis.visualtasks.tasksserver.entities.ProjectConfig;
import ru.itis.visualtasks.tasksserver.exceptions.tasks.ConfigFileReadingException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ProjectConfigUtils {

    private static final Gson GSON = new Gson();

    public static ProjectConfig readProjectConfig(byte[] configFileContent) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(configFileContent),
                        StandardCharsets.UTF_8))){
            return GSON.fromJson(bufferedReader, ProjectConfig.class);
        } catch (IOException ex) {
            throw new ConfigFileReadingException(ex);
        }
    }

}
