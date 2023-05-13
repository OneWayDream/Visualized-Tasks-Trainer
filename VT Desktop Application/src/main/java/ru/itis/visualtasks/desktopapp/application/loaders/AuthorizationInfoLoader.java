package ru.itis.visualtasks.desktopapp.application.loaders;

import com.google.gson.Gson;
import ru.itis.visualtasks.desktopapp.application.entities.project.AuthorizationInfo;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.UserSettingsFileReadingException;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.UserSettingsFileWritingException;
import ru.itis.visualtasks.desktopapp.utils.PropertiesUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AuthorizationInfoLoader {

    public static final String AUTHORIZATION_INFO_FILE_PATH;

    static {
        AUTHORIZATION_INFO_FILE_PATH = PropertiesUtils.getInstance().getProperties().getProperty("settings-path")
                + File.separator + "auth.json";
    }

    public static AuthorizationInfo getAuthorizationInfo(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(AUTHORIZATION_INFO_FILE_PATH,
                StandardCharsets.UTF_8))){
            return new Gson().fromJson(bufferedReader, AuthorizationInfo.class);
        } catch (IOException ex) {
            throw new UserSettingsFileReadingException(ex);
        }
    }

    public static void saveAuthorizationInfo(AuthorizationInfo authorizationInfo){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(AUTHORIZATION_INFO_FILE_PATH,
                StandardCharsets.UTF_8))){
            bufferedWriter.write(new Gson().toJson(authorizationInfo));
        } catch (IOException ex) {
            throw new UserSettingsFileWritingException(ex);
        }
    }

    public static AuthorizationInfo getDefaultAuthorizationInfo(){
        return new AuthorizationInfo();
    }

}
