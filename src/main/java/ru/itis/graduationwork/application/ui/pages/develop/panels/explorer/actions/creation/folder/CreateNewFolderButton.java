package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.actions.creation.folder;

import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.templates.Button;
import ru.itis.graduationwork.exceptions.files.EmptyNewFolderNameException;
import ru.itis.graduationwork.exceptions.files.FolderAlreadyExistsException;
import ru.itis.graduationwork.exceptions.files.FolderCreationException;

import java.awt.event.ActionEvent;

public class CreateNewFolderButton extends Button {

    private final FolderCreationDialog parentDialog;

    public CreateNewFolderButton(FolderCreationDialog creationDialog){
        super();
        parentDialog = creationDialog;
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setText(LocalizationManager.getLocaleTextByKey("ide.content.workspace.explorer.folder-creation-dialog.button.text"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            parentDialog.createNewFolder();
            parentDialog.dispose();
        } catch (FolderCreationException | FolderAlreadyExistsException | EmptyNewFolderNameException exception){
            exception.handle();
        }
    }

}
