package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.actions.deletion;

import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileDeletionException;

import java.awt.event.ActionEvent;

public class DeletionConfirmationButton extends Button {

    private final DeletionDialog parentDialog;

    public DeletionConfirmationButton(DeletionDialog deletionDialog){
        super();
        parentDialog = deletionDialog;
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setText(LocalizationManager.getLocaleTextByKey("ide.content.workspace.explorer.deletion-dialog.button.text"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            parentDialog.delete();
            parentDialog.dispose();
        } catch (FileDeletionException exception){
            exception.handle();
        }
    }

}
