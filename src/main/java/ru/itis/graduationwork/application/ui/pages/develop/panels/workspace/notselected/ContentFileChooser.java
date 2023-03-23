package ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.notselected;

import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.files.FilesManager;
import ru.itis.graduationwork.application.managers.project.WorkspaceContentManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.ide.workspace.ContentTab;
import ru.itis.graduationwork.application.ui.core.templates.Chooser;
import ru.itis.graduationwork.exceptions.files.FileCopyingException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ContentFileChooser extends Chooser {

    private String projectPath;

    @Override
    protected void init(){
        super.init();
        projectPath = ConfigManager.getProjectPath();
        chooser.setCurrentDirectory(new File(projectPath));
        chooser.setDialogTitle(LocalizationManager.getLocaleTextByKey("ide.content.workspace.choose-content-file-panel.content-file-chooser.title"));
        chooser.setDialogType(JFileChooser.FILES_ONLY);
        if (isInformationFile()){
            chooser.setFileFilter(new FileNameExtensionFilter(".md, .html", "md", "html"));
        }
        chooser.setAcceptAllFileFilterUsed(false);
    }

    private boolean isInformationFile(){
        return !WorkspaceContentManager.getContentTab().equals(ContentTab.FILE_EDITOR);
    }

    @Override
    public void execute() {
        if (chooser.showOpenDialog(getCurrentFrame()) == JFileChooser.APPROVE_OPTION) {
            String filePath = String.valueOf(chooser.getSelectedFile());
            try{
                if (!isFileInTheProject(filePath)){
                    String fileCopyPath = getFileCopyPath(filePath);
                    FilesManager.copyFile(filePath, fileCopyPath);
                    filePath = fileCopyPath;
                }
                WorkspaceContentManager.changeContentFilePath(filePath);
            } catch (FileCopyingException exception){
                exception.handle();
            }
        }
    }

    private boolean isFileInTheProject(String filePath){
        return filePath.contains(projectPath);
    }

    private String getFileCopyPath(String filePath){
        return projectPath + File.separator + new File(filePath).getName();
    }

}
