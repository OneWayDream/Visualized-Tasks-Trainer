package ru.itis.graduationwork.application.managers.settings;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.loaders.ImagesLoader;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.NotPresentImageIconException;
import ru.itis.graduationwork.exceptions.usersettings.BackgroundImageSavingException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

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
                ExceptionsManager.addDelayedException(
                        ExceptionsManager::handleNotPresentUserBackgroundImageException, 200, TimeUnit.MILLISECONDS
                );
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
