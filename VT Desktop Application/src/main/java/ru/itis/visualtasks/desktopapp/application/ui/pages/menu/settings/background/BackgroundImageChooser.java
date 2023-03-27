package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings.background;

import ru.itis.visualtasks.desktopapp.application.managers.settings.BackgroundImageManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Chooser;
import ru.itis.visualtasks.desktopapp.exceptions.usersettings.BackgroundImageSavingException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class BackgroundImageChooser extends Chooser {

    @Override
    protected void init(){
        super.init();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        chooser.setDialogTitle(LocalizationManager.getLocaleTextByKey("main-frame.settings.change-background.set-image.dialog-title"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("images (.png, .jpg)", "png", "jpg"));
        chooser.setAcceptAllFileFilterUsed(false);
    }

    @Override
    public void execute(){
        if (chooser.showOpenDialog(getCurrentFrame()) == JFileChooser.APPROVE_OPTION) {
            File selectedImage = chooser.getSelectedFile();
            try{
                BackgroundImageManager.saveNewBackgroundImage(selectedImage);
            } catch (BackgroundImageSavingException exception){
                exception.handle();
            }
        }
    }



}
