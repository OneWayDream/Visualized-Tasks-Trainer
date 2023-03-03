package ru.itis.graduationwork.application.managers;

import ru.itis.graduationwork.application.loaders.SettingsLoader;
import ru.itis.graduationwork.application.settings.UserSettings;
import ru.itis.graduationwork.application.settings.Locale;
import ru.itis.graduationwork.application.settings.Mode;
import ru.itis.graduationwork.application.settings.Theme;
import ru.itis.graduationwork.exceptions.usersettings.UserSettingsFileReadingException;
import ru.itis.graduationwork.exceptions.usersettings.UserSettingsFileWritingException;

import java.util.concurrent.TimeUnit;

public class SettingsManager {

    private static UserSettings settings;

    static {
        try {
            settings = SettingsLoader.getUserSettings();
        } catch (UserSettingsFileReadingException exception){
            settings = SettingsLoader.getDefaultUserSettings();
        }
        setManagersTheme();
        setManagersLocale();
    }

    private static void setManagersTheme(){
        ImagesManager.setTheme(settings.getTheme());
        ColorsManager.setTheme(settings.getTheme());
    }

    private static void setManagersLocale(){
        LocalizationManager.setLocale(settings.getLocale());
    }

    public static Locale getLocale(){
        return settings.getLocale();
    }

    public static void setLocale(Locale locale){
        settings.setLocale(locale);
        setManagersLocale();
        saveSettings();
    }

    public static Theme getTheme(){
        return settings.getTheme();
    }

    public static void setTheme(Theme theme){
        settings.setTheme(theme);
        setManagersTheme();
    }

    public static Mode getMode(){
        return settings.getMode();
    }

    public static void setMode(Mode mode){
        settings.setMode(mode);
    }
    public static void setBackgroundImageName(String backgroundImageName){
        settings.setBackgroundImageName(backgroundImageName);
    }
    public static String getBackgroundImageName(){
        return settings.getBackgroundImageName();
    }

    public static void saveSettings(){
        try {
            SettingsLoader.saveUserSettings(settings);
        } catch (UserSettingsFileWritingException exception){
            ExceptionsManager.addDelayedException(
                    ExceptionsManager::handleUserSettingsFileWritingException, 200, TimeUnit.MILLISECONDS
            );
        }
    }

}
