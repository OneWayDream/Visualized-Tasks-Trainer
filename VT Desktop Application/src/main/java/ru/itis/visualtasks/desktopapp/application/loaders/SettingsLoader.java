package ru.itis.visualtasks.desktopapp.application.loaders;

import com.google.gson.Gson;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.application.settings.UserSettings;
import ru.itis.visualtasks.desktopapp.exceptions.files.FolderAlreadyExistsException;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.UserSettingsFileReadingException;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.UserSettingsFileWritingException;
import ru.itis.visualtasks.desktopapp.utils.PropertiesUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SettingsLoader {

    public static final String USER_SETTINGS_FILE_PATH;

    static {
        String settingsPath = PropertiesUtils.getInstance().getProperties().getProperty("settings-path");
        USER_SETTINGS_FILE_PATH = settingsPath + File.separator + "settings.json";
        if (!FilesManager.checkIsFileExist(settingsPath)){
            try{
                FilesManager.createDirectory(settingsPath);
            } catch (FolderAlreadyExistsException exception){
                //ignore
            }
        }
    }

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
