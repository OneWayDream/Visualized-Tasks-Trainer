package ru.itis.visualtasks.desktopapp.application.managers.content;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import ru.itis.visualtasks.desktopapp.application.settings.Image;

import javax.swing.*;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class IconsManagerTests {

    @Test
    public void get_image_icon(){
        ImageIcon imageIcon = IconsManager.getImageIcon(Image.FILE, 200, 200);
        Assertions.assertNotNull(imageIcon);
        Assertions.assertEquals(200, imageIcon.getIconWidth());
        Assertions.assertEquals(200, imageIcon.getIconHeight());
    }

}
