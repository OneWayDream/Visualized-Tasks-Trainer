package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.actions.creation.file;

import ru.itis.graduationwork.application.generators.FilesGenerator;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.files.FilesManager;
import ru.itis.graduationwork.application.managers.project.FilesGeneratorManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.templates.Dialog;
import ru.itis.graduationwork.exceptions.files.EmptyNewFileNameException;

import java.awt.*;
import java.io.File;

public class FileCreationDialog extends Dialog {

    private final String folderPath;
    private FileNameTextField fileNameTextField;
    private boolean isCreated = false;
    private final FilesGenerator filesGenerator;

    public FileCreationDialog(String folderPath){
        this.folderPath = folderPath;
        filesGenerator = FilesGeneratorManager.getFilesGenerator(ConfigManager.getProjectLanguage());
    }

    @Override
    protected void initFields() {
        width = getDeviceScreenWidth() / 4;
        height = getDeviceScreenHeight() / 4;
        title = LocalizationManager.getLocaleTextByKey("ide.content.workspace.explorer.file-creation-dialog.title");
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
        if (fileName.length() == 0){
            throw new EmptyNewFileNameException();
        } else {
            String filePath = getFilePath();
            if (filesGenerator.checkIsCodeFile(fileName)){
                createNewCodeFile(filePath);
            } else {
                FilesManager.createFile(filePath);
            }
            isCreated = true;
        }
    }

    private void createNewCodeFile(String filePath){
        filesGenerator.generateCodeFile(filePath);
    }

    public boolean isFileCreated(){
        return isCreated;
    }

    public String getFilePath(){
        return folderPath + File.separator + fileNameTextField.getText();
    }

    public void dispose(){
        dialog.dispose();
    }

}
