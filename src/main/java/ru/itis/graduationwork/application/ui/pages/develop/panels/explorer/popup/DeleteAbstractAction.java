package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.popup;

import ru.itis.graduationwork.application.managers.project.WorkspaceContentManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.actions.deletion.DeletionDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteAbstractAction extends AbstractAction {

    private final FileTreePopupMenu fileTreePopupMenu;

    public DeleteAbstractAction(FileTreePopupMenu fileTreePopupMenu){
        super(LocalizationManager.getLocaleTextByKey("ide.content.workspace.explorer.popup-menu.delete.text"));
        this.fileTreePopupMenu = fileTreePopupMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DeletionDialog fileCreationDialog = new DeletionDialog(fileTreePopupMenu.getTriggerPath());
        fileCreationDialog.initDialog();
        if (fileCreationDialog.isDeleted()){
            String filePath = fileCreationDialog.getPath();
            WorkspaceContentManager.notifyAboutFileDeletion(filePath);
        }
    }
}
