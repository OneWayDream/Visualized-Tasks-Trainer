package ru.itis.graduationwork.application.ui.core.ide.workspace.editor;

import ru.itis.graduationwork.application.managers.files.FilesManager;
import ru.itis.graduationwork.application.managers.project.WorkspaceContentManager;
import ru.itis.graduationwork.application.ui.core.templates.TextArea;
import ru.itis.graduationwork.exceptions.files.FileWritingException;

import java.awt.*;

public class FileEditor extends TextArea {

    public FileEditor(){
        super();
    }

    @Override
    protected void initTextArea() {
        textArea.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        textArea.setOpaque(false);
        textArea.setText(WorkspaceContentManager.getFileEditorContent());
    }

    public void saveChanges(){
        try{
            FilesManager.writeFile(WorkspaceContentManager.getEditorFilePath(), textArea.getText());
        } catch (FileWritingException exception){
            exception.handle();
        }
    }

}
