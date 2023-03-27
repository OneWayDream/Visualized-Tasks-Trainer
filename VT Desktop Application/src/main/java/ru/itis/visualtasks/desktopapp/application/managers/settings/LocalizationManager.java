package ru.itis.visualtasks.desktopapp.application.managers.settings;

import ru.itis.visualtasks.desktopapp.application.settings.Locale;
import ru.itis.visualtasks.desktopapp.utils.ConfigurationFilesWorker;
import ru.itis.visualtasks.desktopapp.utils.PropertiesUtils;

import java.util.Map;
import java.util.Properties;

public class LocalizationManager {

    private static Map<String, String> LOCALIZATION_MAP;

    public static String getLocaleTextByKey(String textKey){
        return LOCALIZATION_MAP.get(textKey);
    }

    public static void setLocale(Locale locale){
        Properties properties = PropertiesUtils.getInstance().getProperties();
        String configPath;
        if (locale == Locale.EN){
            configPath = properties.getProperty("locale.en-config");
        } else {
            configPath = properties.getProperty("locale.ru-config");
        }
        LOCALIZATION_MAP = ConfigurationFilesWorker.loadYamlAsMap(configPath);
    }

}
