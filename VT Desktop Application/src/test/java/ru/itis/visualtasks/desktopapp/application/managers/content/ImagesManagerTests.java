package ru.itis.visualtasks.desktopapp.application.managers.content;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import ru.itis.visualtasks.desktopapp.application.settings.Image;

import javax.swing.*;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class ImagesManagerTests {

    @Test
    public void get_image_icon(){
        ImageIcon imageIcon = ImagesManager.getImageIcon(Image.FILE);
        Assertions.assertNotNull(imageIcon);
        Assertions.assertNotEquals(0, imageIcon.getIconWidth());
        Assertions.assertNotEquals(0, imageIcon.getIconHeight());
    }

    @Test
    public void get_application_icon(){
        ImageIcon imageIcon = ImagesManager.getApplicationIcon();
        Assertions.assertNotNull(imageIcon);
        Assertions.assertNotEquals(0, imageIcon.getIconWidth());
        Assertions.assertNotEquals(0, imageIcon.getIconHeight());
    }

}
