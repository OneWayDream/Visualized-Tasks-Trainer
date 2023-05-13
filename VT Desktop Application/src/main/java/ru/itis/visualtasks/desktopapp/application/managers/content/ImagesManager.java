package ru.itis.visualtasks.desktopapp.application.managers.content;

import ru.itis.visualtasks.desktopapp.application.loaders.ImagesLoader;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.NotPresentImageIconException;
import ru.itis.visualtasks.desktopapp.utils.ConfigurationFilesWorker;
import ru.itis.visualtasks.desktopapp.utils.PropertiesUtils;

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
        String imageName = getImageName(image);
        String imagePath = ImagesLoader.getImagePath(imageName, currentTheme);
        try {
            return ImagesLoader.getImageIcon(imagePath);
        } catch (NotPresentImageIconException exception){
            exception.handle();
        }
        return new ImageIcon("");
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
        try{
            return ImagesLoader.getImageIcon(APPLICATION_ICON_PATH);
        } catch (NotPresentImageIconException exception){
            exception.handle();
        }
        return new ImageIcon("");
    }
    
}
