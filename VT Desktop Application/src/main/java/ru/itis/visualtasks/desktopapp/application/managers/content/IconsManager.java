package ru.itis.visualtasks.desktopapp.application.managers.content;

import javax.swing.*;
import java.awt.*;

public class IconsManager {

    public static ImageIcon getImageIcon(ru.itis.visualtasks.desktopapp.application.settings.Image image, int width, int height){
        ImageIcon imageIcon = ImagesManager.getImageIcon(image);
        Image temporaryImage = imageIcon.getImage();
        Image newImage = temporaryImage.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

}
