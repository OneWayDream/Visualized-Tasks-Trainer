package ru.itis.graduationwork.application.ui.pages.main.suppliers;

import ru.itis.graduationwork.application.ui.core.templates.Button;
import ru.itis.graduationwork.application.managers.ImagesManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class ModeComponentsSupplier {


    public abstract ImageIcon getLeftPanelTitleIcon();
    public abstract ImageIcon getRightPanelTitleIcon();
    public abstract String getLeftPanelIconTitle();
    public abstract String getLeftPanelIconDescription();
    public abstract List<Button> getLeftPanelButtons();
    public abstract List<Button> getRightPanelButtons();
    public abstract String getRecentDialogTitle();
    public abstract List<Button> getRecentPageButtons();

    public ImageIcon getImageIcon(ru.itis.graduationwork.application.settings.units.Image image, int width, int height){
        ImageIcon imageIcon = ImagesManager.getImageIcon(image);
        Image temporaryImage = imageIcon.getImage();
        Image newImage = temporaryImage.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

}
