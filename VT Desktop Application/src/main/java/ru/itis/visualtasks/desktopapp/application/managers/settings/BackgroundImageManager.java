package ru.itis.visualtasks.desktopapp.application.managers.settings;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.loaders.ImagesLoader;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.BackgroundImageSavingException;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.NotPresentImageIconException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BackgroundImageManager {

    private static final String BACKGROUND_IMAGE_PATH = "settings/background";

    public static void saveNewBackgroundImage(File sourceImage){
        try{
            File targetImage = new File(BACKGROUND_IMAGE_PATH + File.separator + sourceImage.getName());
            targetImage.createNewFile();
            Files.copy(sourceImage.toPath(), targetImage.toPath(), StandardCopyOption.REPLACE_EXISTING);
            deleteOldImageIfExists();
            SettingsManager.setBackgroundImageName(sourceImage.getName());
            Application.reloadPage();
        } catch (IOException ex) {
            throw new BackgroundImageSavingException(ex);
        }
    }

    private static void deleteOldImageIfExists(){
        String oldImage = SettingsManager.getBackgroundImageName();
        if (oldImage != null){
            deleteOldImage(oldImage);
        }
    }

    private static void deleteOldImage(String oldImage){
        File fileToDelete = new File(BACKGROUND_IMAGE_PATH + File.separator + oldImage);
        fileToDelete.delete();
    }

    public static Image getBackgroundImage() {
        String backgroundImageName = SettingsManager.getBackgroundImageName();
        if (backgroundImageName != null) {
            try {
                ImageIcon imageIcon = ImagesLoader.getImageIcon(BACKGROUND_IMAGE_PATH + File.separator + backgroundImageName);
                return imageIcon.getImage();
            } catch (NotPresentImageIconException exception){
                exception.handle();
                SettingsManager.setBackgroundImageName(null);
            }
        }
        return null;
    }

    public static void deleteBackgroundImage(){
        deleteOldImageIfExists();
        SettingsManager.setBackgroundImageName(null);
        Application.reloadPage();
    }

}
