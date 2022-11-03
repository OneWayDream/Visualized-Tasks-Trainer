package ru.itis.graduationwork.application.managers;

import ru.itis.graduationwork.application.loaders.SettingsLoader;
import ru.itis.graduationwork.application.settings.entities.UserSettings;
import ru.itis.graduationwork.application.settings.units.Locale;
import ru.itis.graduationwork.application.settings.units.Theme;
import ru.itis.graduationwork.application.ui.pages.main.MainPageFrame;

public class SettingsManager {

    private static final UserSettings settings;

    static {
        settings = SettingsLoader.getUserSettings();
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
    }

    public static Theme getTheme(){
        return settings.getTheme();
    }

    public static void setTheme(Theme theme){
        settings.setTheme(theme);
        setManagersTheme();
    }

    public static MainPageFrame.Mode getMode(){
        return settings.getMode();
    }

    public static void setMode(MainPageFrame.Mode mode){
        settings.setMode(mode);
    }

    public static void saveSettings(){
        SettingsLoader.saveUserSettings(settings);
    }

}
