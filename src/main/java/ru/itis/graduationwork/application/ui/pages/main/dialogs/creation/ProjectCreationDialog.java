package ru.itis.graduationwork.application.ui.pages.main.dialogs.creation;

import ru.itis.graduationwork.application.entities.project.Language;
import ru.itis.graduationwork.application.entities.project.NewProjectInfo;
import ru.itis.graduationwork.application.entities.project.VisualizationType;
import ru.itis.graduationwork.application.managers.project.ProjectsManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.templates.Dialog;
import ru.itis.graduationwork.exceptions.project.ProjectCreationException;
import ru.itis.graduationwork.exceptions.unexpected.UnsupportedLanguageException;
import ru.itis.graduationwork.exceptions.unexpected.UnsupportedVisualizationTypeException;
import ru.itis.graduationwork.exceptions.files.FileNotFoundException;
import ru.itis.graduationwork.exceptions.files.FileReadingException;

import javax.swing.*;
import java.awt.*;

public class ProjectCreationDialog extends Dialog {

    private String projectName;
    private String projectPath;

    private ProjectNameTextField projectNameTextField;
    private ProjectPathTextField projectPathTextField;
    private JComboBox<String> languageSelectBox;
    private JComboBox<String> visualizationTypeSelectBox;

    @Override
    protected void initFields() {
        width = getDeviceScreenWidth() / 2;
        height = getDeviceScreenHeight() / 2;
        title = LocalizationManager.getLocaleTextByKey("main-frame.project-creation.title");
    }

    @Override
    public void initDialog(){
        dialog.setLayout(new GridBagLayout());
        dialog.setResizable(true);
        super.initDialog();
    }

    @Override
    protected void addComponents() {
        addProjectNameRow();
        addProjectPathRow();
        addLanguageRow();
        addVisualizationTypeRow();
        addCreateButton();
    }

    private void addProjectNameRow(){
        addProjectNameFieldTitle();
        addProjectNameTextField();
    }

    private void addProjectNameFieldTitle(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.weightx = 0.3;
        constraint.insets = new Insets(50,60,0,0);
        dialog.add(new ProjectCreationFieldTitle(
                LocalizationManager.getLocaleTextByKey("main-frame.project-creation.name-field.title") + ":"
        ).getComponent(), constraint);
    }

    private void addProjectNameTextField(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.weightx = 0.6;
        constraint.insets = new Insets(50,-60,0,0);
        projectNameTextField = new ProjectNameTextField();
        projectNameTextField.setText(projectName);
        dialog.add(projectNameTextField.getComponent(), constraint);
    }

    private void addProjectPathRow(){
        addProjectPathFieldTitle();
        addProjectPathTextField();
        addProjectPathSelectButton();

    }

    private void addProjectPathFieldTitle() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.weightx = 0.3;
        constraint.insets = new Insets(50,60,0,0);
        dialog.add(new ProjectCreationFieldTitle(
                LocalizationManager.getLocaleTextByKey("main-frame.project-creation.path-field.title") + ":"
        ).getComponent(), constraint);
    }

    private void addProjectPathTextField() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.weightx = 0.6;
        constraint.insets = new Insets(50,-60,0,0);
        projectPathTextField = new ProjectPathTextField();
        projectPathTextField.setText(getDefaultProjectPath());
        dialog.add(projectPathTextField.getComponent(), constraint);
    }

    private String getDefaultProjectPath(){
        if (projectPath == null){
            return System.getProperty("user.dir");
        } else {
            return projectPath;
        }
    }

    private void addProjectPathSelectButton(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridx = 2;
        constraint.gridy = 1;
        constraint.weightx = 0.1;
        constraint.insets = new Insets(50,-100,0,0);
        ChoosePathButton choosePathButton = new ChoosePathButton(projectPathTextField, projectNameTextField);
        dialog.add(choosePathButton.getComponent(), constraint);
    }

    private void addLanguageRow(){
        addLanguageFieldTitle();
        addLanguageSelectBox();
    }

    private void addLanguageFieldTitle(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.weightx = 0.3;
        constraint.insets = new Insets(-200,60,0,0);
        dialog.add(new ProjectCreationFieldTitle(
                LocalizationManager.getLocaleTextByKey("main-frame.project-creation.language-field.title") + ":"
        ).getComponent(), constraint);
    }

    private void addLanguageSelectBox(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.weightx = 0.6;
        constraint.insets = new Insets(-200,-225,0,100);
        languageSelectBox = new JComboBox<>(Language.getLanguagesNames());
        dialog.add(languageSelectBox, constraint);
    }

    private void addVisualizationTypeRow(){
        addVisualizationTypeTitle();
        addVisualizationTypeSelectBox();
    }

    private void addVisualizationTypeTitle() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.weightx = 0.3;
        constraint.insets = new Insets(-400,60,0,0);
        dialog.add(new ProjectCreationFieldTitle(
                LocalizationManager.getLocaleTextByKey("main-frame.project-creation.visualization-type-field.title") + ":"
        ).getComponent(), constraint);
    }

    private void addVisualizationTypeSelectBox() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridx = 1;
        constraint.gridy = 3;
        constraint.weightx = 0.6;
        constraint.insets = new Insets(-400,-225,0,100);
        visualizationTypeSelectBox = new JComboBox<>(VisualizationType.getVisualizationTypeNames());
        dialog.add(visualizationTypeSelectBox, constraint);
    }

    private void addCreateButton(){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 5;
        c.gridy = 2;
        c.weighty = 1.0;
        c.insets = new Insets(0,0,30,50);
        dialog.add(new CreateProjectButton(this).getComponent(), c);
    }

    public void createProject(){
        NewProjectInfo newProjectInfo = getNewProjectInfo();
        try {
            ProjectsManager.createProject(newProjectInfo);
            ProjectsManager.openProject(newProjectInfo.getProjectPath());
        } catch (ProjectCreationException | UnsupportedLanguageException |
                 UnsupportedVisualizationTypeException exception){
            exception.handle();
        } catch (FileNotFoundException | FileReadingException exception){
            new ProjectCreationException().handle();
        }
    }

    private NewProjectInfo getNewProjectInfo(){
        return NewProjectInfo.builder()
                .projectName(projectNameTextField.getText())
                .projectPath(projectPathTextField.getText())
                .language(Language.getByName((String) languageSelectBox.getSelectedItem()))
                .visualizationType(VisualizationType.getByName((String) visualizationTypeSelectBox.getSelectedItem()))
                .build();
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

}
