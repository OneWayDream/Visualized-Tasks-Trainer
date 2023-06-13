package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.actions.creation.folder;

import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Dialog;
import ru.itis.visualtasks.desktopapp.exceptions.files.EmptyNewFolderNameException;

import java.awt.*;
import java.io.File;

public class FolderCreationDialog extends Dialog {

    private final String folderPath;
    private FolderNameTextField folderNameTextField;

    public FolderCreationDialog(String folderPath){
        this.folderPath = folderPath;
    }

    @Override
    protected void initFields() {
        width = getDeviceScreenWidth() / 4;
        height = getDeviceScreenHeight() / 4;
        title = LocalizationManager.getLocaleTextByKey("ide.content.workspace.explorer.folder-creation-dialog.title");
    }

    @Override
    public void initDialog(){
        dialog.setLayout(new GridBagLayout());
        dialog.setResizable(false);
        super.initDialog();
    }

    @Override
    protected void addComponents() {
        addFolderNameTextField();
        addCreationButton();
    }

    private void addFolderNameTextField() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(0, 30, 0, 30);
        folderNameTextField = new FolderNameTextField();
        dialog.add(folderNameTextField.getComponent(), constraint);
    }

    private void addCreationButton() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.PAGE_END;
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 0;
        c.gridy = 1;
        dialog.add(new CreateNewFolderButton(this).getComponent(), c);
    }

    public void createNewFolder(){
        String folderName = folderNameTextField.getText();
        if (folderName.length() == 0){
            throw new EmptyNewFolderNameException();
        }
        String newFolderPath = getNewFolderPath();
        FilesManager.createDirectory(newFolderPath);
    }

    private String getNewFolderPath(){
        return folderPath + File.separator + folderNameTextField.getText();
    }

    public void dispose(){
        dialog.dispose();
    }

}
