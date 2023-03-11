package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.popup;

import ru.itis.graduationwork.application.managers.project.WorkspaceContentManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.actions.creation.FileCreationDialog;
import ru.itis.graduationwork.exceptions.UnexpectedContentTabException;

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
                ExceptionsManager.handleUnexpectedContentTabException();
            }
        }
    }

}
