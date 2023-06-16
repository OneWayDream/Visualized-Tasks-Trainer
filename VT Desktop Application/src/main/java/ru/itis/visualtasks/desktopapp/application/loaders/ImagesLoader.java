package ru.itis.visualtasks.desktopapp.application.loaders;

import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.NotPresentImageIconException;
import ru.itis.visualtasks.desktopapp.utils.PropertiesUtils;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ImagesLoader {

    private static final String LIGHT_IMAGES_FOLDER;
    private static final String DARK_IMAGES_FOLDER;

    static {
        Properties properties = PropertiesUtils.getInstance().getProperties();
        LIGHT_IMAGES_FOLDER = properties.getProperty("light-theme.images-folder");
        DARK_IMAGES_FOLDER = properties.getProperty("dark-theme.images-folder");
    }

    public static ImageIcon getImageIcon(String imageIconPath){
        ImageIcon imageIcon;
        try (InputStream in = ImagesLoader.class.getClassLoader().getResourceAsStream(imageIconPath)) {
            imageIcon = new ImageIcon(in.readAllBytes());
        } catch (IOException e) {
            throw new NotPresentImageIconException(e.getMessage());
        }
        if (!isPresentImageIcon(imageIcon)){
            throw new NotPresentImageIconException(imageIconPath);
        }
        return imageIcon;
    }

    public static boolean isPresentImageIcon(ImageIcon imageIcon){
        return imageIcon.getIconHeight() != -1;
    }

    public static String getImagePath(String imageName, Theme theme){
        String filePath;
        if (theme == Theme.DARK){
            filePath = LIGHT_IMAGES_FOLDER + imageName;
        } else {
            filePath = DARK_IMAGES_FOLDER + imageName;
        }
        return filePath;
    }

}
