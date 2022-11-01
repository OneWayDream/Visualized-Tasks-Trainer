package ru.itis.graduationwork.application.ui.pages.main.suppliers;

import ru.itis.graduationwork.application.ui.core.Button;
import ru.itis.graduationwork.application.utils.ImagesManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class ComponentsSupplier {


    public abstract ImageIcon getLeftPanelTitleIcon();
    public abstract ImageIcon getRightPanelTitleIcon();
    public abstract String getLeftPanelIconTitle();
    public abstract String getLeftPanelIconDescription();
    public abstract List<Button> getLeftPanelButtons();
    public abstract List<Button> getRightPanelButtons();

    public ImageIcon getImageIcon(ru.itis.graduationwork.application.settings.Image image, int width, int height){
        ImageIcon imageIcon = ImagesManager.getImageIcon(image);
        Image temporaryImage = imageIcon.getImage();
        Image newImage = temporaryImage.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

}
