package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.actions.creation;

import ru.itis.graduationwork.application.entities.Language;
import ru.itis.graduationwork.application.loaders.TemplatesLoader;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.files.FilesManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.application.ui.core.templates.Dialog;

import java.awt.*;
import java.io.File;

public class FileCreationDialog extends Dialog {

    private final String folderPath;
    private FileNameTextField fileNameTextField;
    private boolean isCreated = false;

    public FileCreationDialog(String folderPath){
        this.folderPath = folderPath;
    }

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
        if (fileName.length() == 0){
            ExceptionsManager.handleEmptyNewFileNameException();
        } else {
            String fileExtension = getFileExtension(fileName);
            if (fileExtension.equals(Language.JAVA.getExtension())){
                createNewJavaFile(fileName);
            } else {
                String filePath = getFilePath();
                FilesManager.createFile(filePath);
            }
            isCreated = true;
        }
    }

    private String getFileExtension(String fileName){
        String fileExtension = "";
        if (fileName.contains(".")){
            fileExtension = fileName.substring(fileName.lastIndexOf('.'));
        }
        return fileExtension;
    }

    private void createNewJavaFile(String fileName){
        String fileContent = TemplatesLoader.getJavaFileTemplateFileTemplateContent(Language.JAVA);
        String preparedFileContent = String.format(fileContent, getFileName(fileName));
        preparedFileContent = getPackagePart() + preparedFileContent;
        FilesManager.writeFile(getFilePath(), preparedFileContent);
    }

    private String getFileName(String fileName){
        return fileName.substring(0, fileName.length() - 5);
    }

    private String getPackagePart(){
        StringBuilder packagePart = new StringBuilder();
        if (!folderPath.equals(ConfigManager.getProjectPath())){
            String packagePath = folderPath.substring(ConfigManager.getProjectPath().length() + 1);
            String packageValue = packagePath.replace(File.separatorChar, '.');
            packagePart.append("package ").append(packageValue).append(";\n\n");
            if (packagePart.toString().contains("wrappers")){
                packagePart.append("import ru.itis.graduationwork.application.managers.project.VisualizationActionsManager;\n")
                        .append("import ru.itis.graduationwork.application.entities.VisualizationAction;\n\n");
            }
        }
        return packagePart.toString();
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
