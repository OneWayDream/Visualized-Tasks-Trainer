package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.popup;

import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.actions.creation.folder.FolderCreationDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CreateFolderAbstractAction extends AbstractAction {
    private final FileTreePopupMenu fileTreePopupMenu;

    public CreateFolderAbstractAction(FileTreePopupMenu fileTreePopupMenu){
        super(LocalizationManager.getLocaleTextByKey("ide.content.workspace.explorer.popup-menu.create-folder.text"));
        this.fileTreePopupMenu = fileTreePopupMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FolderCreationDialog folderCreationDialog = new FolderCreationDialog(fileTreePopupMenu.getTriggerPath());
        folderCreationDialog.initDialog();
    }
}
