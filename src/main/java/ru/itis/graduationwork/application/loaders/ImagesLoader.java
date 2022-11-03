package ru.itis.graduationwork.application.loaders;

import lombok.Getter;
import ru.itis.graduationwork.application.settings.units.Theme;
import ru.itis.graduationwork.utils.PropertiesUtils;

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
        return new ImageIcon(imageIconPath);
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
