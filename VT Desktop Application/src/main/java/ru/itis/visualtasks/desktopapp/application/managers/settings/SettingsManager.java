package ru.itis.visualtasks.desktopapp.application.managers.settings;

import ru.itis.visualtasks.desktopapp.application.loaders.SettingsLoader;
import ru.itis.visualtasks.desktopapp.application.managers.content.ImagesManager;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.application.settings.UserSettings;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.UserSettingsFileReadingException;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.UserSettingsFileWritingException;

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
            exception.handle();
        }
    }

}
