package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.actions.creation;

import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.templates.Button;
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
        button.setText(LocalizationManager.getLocaleTextByKey("ide.content.workspace.choose-content-file-panel.creation-dialog.button.text"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            parentDialog.createNewFile();
            parentDialog.dispose();
        } catch (FileCreationException exception){
            ExceptionsManager.handleFileCreationException();
        } catch (FileAlreadyExistsException exception){
            ExceptionsManager.handleFileAlreadyExistsException();
        }
    }

}
