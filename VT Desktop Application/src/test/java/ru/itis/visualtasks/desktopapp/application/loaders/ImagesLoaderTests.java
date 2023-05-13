package ru.itis.visualtasks.desktopapp.application.loaders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class ImagesLoaderTests {

    @ParameterizedTest
    @ValueSource(strings = {"image.png"})
    public void get_for_light_image_path(String imageName){
        Assertions.assertTrue(ImagesLoader.getImagePath(imageName, Theme.DARK)
                .startsWith("images/light/"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"image.png"})
    public void get_for_dark_image_path(String imageName){
        Assertions.assertTrue(ImagesLoader.getImagePath(imageName, Theme.LIGHT)
                .startsWith("images/dark/"));
    }

    @Test
    public void get_for_light_image_icon(){
        Assertions.assertNotNull(ImagesLoader.getImageIcon(
                ImagesLoader.getImagePath(Image.IMAGE.getKey(), Theme.LIGHT) + ".png"));
    }

    @Test
    public void get_for_dark_image_icon(){
        Assertions.assertNotNull(ImagesLoader.getImageIcon(
                ImagesLoader.getImagePath(Image.IMAGE.getKey(), Theme.DARK) + ".png"));
    }

}
