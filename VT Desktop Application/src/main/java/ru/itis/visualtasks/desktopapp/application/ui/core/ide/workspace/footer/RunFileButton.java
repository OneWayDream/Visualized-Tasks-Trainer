package ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.footer;

import ru.itis.visualtasks.desktopapp.application.managers.content.IconsManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectTaskFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.WorkspaceContentManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.IdeFramesIconsConstants;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileWritingException;
import ru.itis.visualtasks.desktopapp.exceptions.files.WrapperFilesReadingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RunFileButton extends ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button {

    public RunFileButton(){
        super();
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        button.setAlignmentX(Component.RIGHT_ALIGNMENT);
        setIcon();
        button.setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.workspace.editor-footer.run-file-button.tooltip-text"));
    }

    private void setIcon(){
        button.setIcon(IconsManager.getImageIcon(Image.RUN,
                IdeFramesIconsConstants.WORKSPACE_EDITOR_FOOTER_ICON_WIDTH,
                IdeFramesIconsConstants.WORKSPACE_EDITOR_FOOTER_ICON_HEIGHT));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            WorkspaceContentManager.saveEditorChangesIfNeeded();
            if (isSolutionFile()){
                ProjectTaskFilesManager.executeSolutionFile();
            } else if (isTestSolutionFile()){
                ProjectTaskFilesManager.executeTestSolutionFile();
            }
        } catch (FileWritingException | WrapperFilesReadingException exception){
            exception.handle();
        }
    }

    private boolean isSolutionFile() {
        String editorFilePath = WorkspaceContentManager.getEditorFilePath();
        return editorFilePath != null && editorFilePath.equals(ProjectFilesManager.getSolutionFilePath());
    }

    private boolean isTestSolutionFile() {
        String editorFilePath = WorkspaceContentManager.getEditorFilePath();
        return editorFilePath != null && editorFilePath.equals(ProjectFilesManager.getTestSolutionFilePath());
    }

}
