package ru.itis.graduationwork.application.managers;

import ru.itis.graduationwork.application.settings.Locale;
import ru.itis.graduationwork.utils.ConfigurationFilesWorker;
import ru.itis.graduationwork.utils.PropertiesUtils;

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
