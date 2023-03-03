package ru.itis.graduationwork.application.loaders;

import com.google.gson.Gson;
import ru.itis.graduationwork.application.settings.Locale;
import ru.itis.graduationwork.application.settings.Mode;
import ru.itis.graduationwork.application.settings.Theme;
import ru.itis.graduationwork.application.settings.UserSettings;
import ru.itis.graduationwork.exceptions.usersettings.UserSettingsFileReadingException;
import ru.itis.graduationwork.exceptions.usersettings.UserSettingsFileWritingException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SettingsLoader {

    public static final String USER_SETTINGS_FILE_PATH = "settings/settings.json";

    public static UserSettings getUserSettings(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(USER_SETTINGS_FILE_PATH,
                StandardCharsets.UTF_8))){
            return new Gson().fromJson(bufferedReader, UserSettings.class);
        } catch (IOException ex) {
            throw new UserSettingsFileReadingException(ex);
        }
    }

    public static void saveUserSettings(UserSettings settings){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(USER_SETTINGS_FILE_PATH,
                StandardCharsets.UTF_8))){
            bufferedWriter.write(new Gson().toJson(settings));
        } catch (IOException ex) {
            throw new UserSettingsFileWritingException(ex);
        }
    }

    public static UserSettings getDefaultUserSettings(){
        return UserSettings.builder()
                .locale(Locale.EN)
                .mode(Mode.DEVELOP)
                .theme(Theme.DARK)
                .build();
    }

}
