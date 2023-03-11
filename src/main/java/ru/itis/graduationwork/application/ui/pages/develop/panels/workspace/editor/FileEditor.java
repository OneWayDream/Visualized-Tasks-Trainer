package ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.editor;

import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.application.managers.files.FilesManager;
import ru.itis.graduationwork.application.managers.project.WorkspaceContentManager;
import ru.itis.graduationwork.application.ui.core.templates.TextArea;
import ru.itis.graduationwork.exceptions.files.FileWritingException;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class FileEditor extends TextArea {

    public FileEditor(){
        super();
    }

    @Override
    protected void initTextArea() {
        textArea.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        textArea.setBackground(ColorsManager.getPanelBackgroundColor());
        textArea.setText(WorkspaceContentManager.getFileEditorContent());
    }

    public void saveChanges(){
        try{
            FilesManager.writeFile(WorkspaceContentManager.getEditorFilePath(), textArea.getText());
        } catch (FileWritingException exception){
            ExceptionsManager.addDelayedException(
                ExceptionsManager::handleFileWritingException, 200, TimeUnit.MILLISECONDS
            );
        }
    }

}
