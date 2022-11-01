package ru.itis.graduationwork.application.utils;

import com.google.gson.Gson;
import ru.itis.graduationwork.application.settings.UserSettings;
import ru.itis.graduationwork.exceptions.application.JsonReadException;
import ru.itis.graduationwork.exceptions.application.JsonWriteException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigurationsManager {

    public static final String USER_SETTINGS_FILE_PATH = "config.json";

    public static UserSettings getUserSettings(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(USER_SETTINGS_FILE_PATH,
                StandardCharsets.UTF_8))){
            return new Gson().fromJson(bufferedReader, UserSettings.class);
        } catch (IOException ex) {
            throw new JsonReadException(ex);
        }
    }

    public static void saveUserSettings(UserSettings settings){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(USER_SETTINGS_FILE_PATH,
                StandardCharsets.UTF_8))){
            bufferedWriter.write(new Gson().toJson(settings));
        } catch (IOException ex) {
            throw new JsonWriteException(ex);
        }
    }

}
