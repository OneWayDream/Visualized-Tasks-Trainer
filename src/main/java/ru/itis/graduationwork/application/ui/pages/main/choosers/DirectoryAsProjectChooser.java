package ru.itis.graduationwork.application.ui.pages.main.choosers;

import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.managers.ProjectsManager;
import ru.itis.graduationwork.application.ui.core.templates.Chooser;

import javax.swing.*;
import java.io.File;

public class DirectoryAsProjectChooser extends Chooser {

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
        if (chooser.showOpenDialog(getCurrentFrame()) == JFileChooser.APPROVE_OPTION) {
            String projectPath = String.valueOf(chooser.getSelectedFile());
            ProjectsManager.openProject(projectPath);
        }
    }

}
