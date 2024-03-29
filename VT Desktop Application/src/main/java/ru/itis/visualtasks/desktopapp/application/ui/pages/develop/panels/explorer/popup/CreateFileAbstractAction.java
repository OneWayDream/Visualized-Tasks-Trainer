package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.popup;

import ru.itis.visualtasks.desktopapp.application.managers.project.WorkspaceContentManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.actions.creation.file.FileCreationDialog;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnexpectedContentTabException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CreateFileAbstractAction extends AbstractAction {

    private final FileTreePopupMenu fileTreePopupMenu;

    public CreateFileAbstractAction(FileTreePopupMenu fileTreePopupMenu){
        super(LocalizationManager.getLocaleTextByKey("ide.content.workspace.explorer.popup-menu.create-file.text"));
        this.fileTreePopupMenu = fileTreePopupMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileCreationDialog fileCreationDialog = new FileCreationDialog(fileTreePopupMenu.getTriggerPath());
        fileCreationDialog.initDialog();
        if (fileCreationDialog.isFileCreated()){
            String filePath = fileCreationDialog.getFilePath();
            try {
                WorkspaceContentManager.setEditorContent(filePath);
            } catch (UnexpectedContentTabException exception){
                exception.handle();
            }
        }
    }

}
