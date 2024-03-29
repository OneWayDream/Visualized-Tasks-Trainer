package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.actions.creation.file;

import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button;
import ru.itis.visualtasks.desktopapp.exceptions.files.EmptyNewFileNameException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileAlreadyExistsException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileCreationException;

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
