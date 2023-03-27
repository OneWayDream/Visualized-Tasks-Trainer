package ru.itis.visualtasks.desktopapp.application.loaders;

import lombok.Getter;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.NotPresentImageIconException;
import ru.itis.visualtasks.desktopapp.utils.PropertiesUtils;

import javax.swing.*;
import java.util.Properties;

public class ImagesLoader {
    @Getter
    private static final String LIGHT_IMAGES_FOLDER;
    @Getter
    private static final String DARK_IMAGES_FOLDER;

    static {
        Properties properties = PropertiesUtils.getInstance().getProperties();
        LIGHT_IMAGES_FOLDER = properties.getProperty("light-theme.images-folder");
        DARK_IMAGES_FOLDER = properties.getProperty("dark-theme.images-folder");
    }

    public static ImageIcon getImageIcon(String imageIconPath){
        ImageIcon imageIcon = new ImageIcon(imageIconPath);
        if (!isPresentImageIcon(imageIcon)){
            throw new NotPresentImageIconException();
        }
        return new ImageIcon(imageIconPath);
    }

    private static boolean isPresentImageIcon(ImageIcon imageIcon){
        return imageIcon.getIconHeight() != -1;
    }

    public static String getFilePath(String fileName, Theme theme){
        String filePath;
        if (theme == Theme.DARK){
            filePath = LIGHT_IMAGES_FOLDER + fileName;
        } else {
            filePath = DARK_IMAGES_FOLDER + fileName;
        }
        return filePath;
    }

}
