package ru.itis.graduationwork.application.utils;

import ru.itis.graduationwork.exceptions.application.NotInitializedThemeException;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.settings.Theme;
import ru.itis.graduationwork.utils.ConfigurationFilesWorker;
import ru.itis.graduationwork.utils.PropertiesUtils;

import javax.swing.*;
import java.util.Map;
import java.util.Properties;

public class ImagesManager {

    private static final String APPLICATION_ICON_PATH;
    private static Map<String, String> IMAGES_KEYS_MAP;
    private static Theme currentTheme;

    static {
        Properties properties = PropertiesUtils.getInstance().getProperties();
        APPLICATION_ICON_PATH = properties.getProperty("application-icon-path");
    }

    private static Map<String, String> loadImagesConfig(String configPath){
        Properties properties = ConfigurationFilesWorker.loadProperties(configPath);
        return ConfigurationFilesWorker.propertiesToMap(properties);
    }

    public static ImageIcon getImageIcon(Image image){
        if (currentTheme == null){
            throw new NotInitializedThemeException();
        }
        String imageName = getImageName(image);
        String imagePath = ImagesLoader.getFilePath(imageName, currentTheme);
        return ImagesLoader.getImageIcon(imagePath);
    }

    private static String getImageName(Image image){
        return IMAGES_KEYS_MAP.get(image.getKey());
    }

    public static void setTheme(Theme theme){
        Properties properties = PropertiesUtils.getInstance().getProperties();
        currentTheme = theme;
        if (theme == Theme.DARK){
            String lightImagesConfigPath = properties.getProperty("light-theme.images-config");
            IMAGES_KEYS_MAP = loadImagesConfig(lightImagesConfigPath);
        } else {
            String darkImagesConfigPath = properties.getProperty("dark-theme.images-config");
            IMAGES_KEYS_MAP = loadImagesConfig(darkImagesConfigPath);
        }
    }

    public static ImageIcon getApplicationIcon(){
        return ImagesLoader.getImageIcon(APPLICATION_ICON_PATH);
    }
    
}
