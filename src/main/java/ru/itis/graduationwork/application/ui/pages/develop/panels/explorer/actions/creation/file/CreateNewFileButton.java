package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.actions.creation.file;

import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.templates.Button;
import ru.itis.graduationwork.exceptions.files.EmptyNewFileNameException;
import ru.itis.graduationwork.exceptions.files.FileAlreadyExistsException;
import ru.itis.graduationwork.exceptions.files.FileCreationException;

import java.awt.event.ActionEvent;

public class CreateNewFileButton extends Button {

    private final FileCreationDialog parentDialog;

    public CreateNewFileButton(FileCreationDialog creationDialog){
        super();
        parentDialog = creationDialog;
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setText(LocalizationManager.getLocaleTextByKey("ide.content.workspace.explorer.file-creation-dialog.button.text"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            parentDialog.createNewFile();
            parentDialog.dispose();
        } catch (EmptyNewFileNameException | FileAlreadyExistsException | FileCreationException exception){
            exception.handle();
        }
    }

}
