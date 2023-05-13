package ru.itis.visualtasks.desktopapp.application.managers.settings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;

import java.io.File;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class BackgroundImageManagerTests {

    private static final String IMAGE_PATH = USER_DIRECTORY + File.separator
            + "src\\test\\resources\\managers\\settings\\background-image.jpg";
    private static final String SETTINGS_BACKGROUND_IMAGE_PATH = USER_DIRECTORY + File.separator
            + "test_settings\\background\\background-image.jpg";

    @Test
    public void background_image_setting_lifecycle(){
        BackgroundImageManager.saveNewBackgroundImage(new File(IMAGE_PATH));
        Assertions.assertTrue(FilesManager.checkIsFileExist(SETTINGS_BACKGROUND_IMAGE_PATH));
        Assertions.assertNotNull(BackgroundImageManager.getBackgroundImage());
        BackgroundImageManager.deleteBackgroundImage();
        Assertions.assertFalse(FilesManager.checkIsFileExist(SETTINGS_BACKGROUND_IMAGE_PATH));
    }

}
