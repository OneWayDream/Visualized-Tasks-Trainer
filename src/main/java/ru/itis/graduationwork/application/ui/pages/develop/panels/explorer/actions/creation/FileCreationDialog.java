package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.actions.creation;

import ru.itis.graduationwork.application.managers.ConfigManager;
import ru.itis.graduationwork.application.managers.FilesManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.templates.Dialog;

import java.awt.*;
import java.io.File;

public class FileCreationDialog extends Dialog {

    private FileNameTextField fileNameTextField;
    private boolean isCreated = false;

    @Override
    protected void initFields() {
        width = getDeviceScreenWidth() / 4;
        height = getDeviceScreenHeight() / 4;
        title = LocalizationManager.getLocaleTextByKey("ide.content.workspace.choose-content-file-panel.creation-dialog.title");
    }

    @Override
    public void initDialog(){
        dialog.setLayout(new GridBagLayout());
        dialog.setResizable(false);
        super.initDialog();
    }

    @Override
    protected void addComponents() {
        addFileNameTextField();
        addCreationButton();
    }

    private void addFileNameTextField() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(0, 30, 0, 30);
        fileNameTextField = new FileNameTextField();
        dialog.add(fileNameTextField.getComponent(), constraint);
    }

    private void addCreationButton() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.PAGE_END;
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 0;
        c.gridy = 1;
        dialog.add(new CreateNewFileButton(this).getComponent(), c);
    }

    public void createNewFile(){
        String fileName = fileNameTextField.getText();
        String filePath = ConfigManager.getProjectPath() + File.separator + fileName;
        FilesManager.createFile(filePath);
        isCreated = true;
    }

    public boolean isFileCreated(){
        return isCreated;
    }

    public String getFilePath(){
        return ConfigManager.getProjectPath() + File.separator + fileNameTextField.getText();
    }

    public void dispose(){
        dialog.dispose();
    }

}
