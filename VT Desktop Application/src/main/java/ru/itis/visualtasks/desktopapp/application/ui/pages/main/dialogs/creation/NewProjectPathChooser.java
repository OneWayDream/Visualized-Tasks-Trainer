package ru.itis.visualtasks.desktopapp.application.ui.pages.main.dialogs.creation;

import lombok.Getter;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Chooser;

import javax.swing.*;
import java.io.File;

public class NewProjectPathChooser extends Chooser {

    @Getter
    private String selectedDirectory;

    @Override
    protected void init(){
        super.init();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        chooser.setDialogTitle(LocalizationManager.getLocaleTextByKey("main-frame.open-as-project.title"));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
    }

    @Override
    public void execute(){
        if (chooser.showOpenDialog(getCurrentFrame()) == JFileChooser.APPROVE_OPTION)
            selectedDirectory = String.valueOf(chooser.getSelectedFile());
        else {
            selectedDirectory = null;
        }
    }

}
