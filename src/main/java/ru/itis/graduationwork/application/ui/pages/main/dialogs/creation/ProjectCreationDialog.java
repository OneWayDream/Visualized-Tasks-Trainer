package ru.itis.graduationwork.application.ui.pages.main.dialogs.creation;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.templates.Dialog;

import javax.swing.*;
import java.awt.*;

public class ProjectCreationDialog extends Dialog {

    private ProjectNameTextField projectNameTextField;
    private JComboBox<String> languageSelectBox;

    public ProjectCreationDialog() {
        super(Application.getCurrentPageFrame().getFrame());
    }

    @Override
    protected void initFields() {
        width = getDeviceScreenWidth() / 2;
        height = getDeviceScreenHeight() / 2;
        title = LocalizationManager.getLocaleTextByKey("main-frame.project-creation.title");
    }

    @Override
    public void initDialog(){
        dialog.setLayout(new GridBagLayout());
        super.initDialog();
    }

    @Override
    protected void addComponents() {
        addProjectNameRow();
        addLanguageRow();
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
        constraint.insets = new Insets(50,130,0,0);
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
        constraint.insets = new Insets(50,0,0,100);
        projectNameTextField = new ProjectNameTextField();
        dialog.add(projectNameTextField.getComponent(), constraint);
    }

    private void addLanguageRow(){
        addLanguageFieldTitle();
        addLanguageSelectBox();
    }

    private void addLanguageFieldTitle(){
        GridBagConstraints constraint = new GridBagConstraints();

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.weightx = 0.3;
        constraint.insets = new Insets(50,130,0,0);
        dialog.add(new ProjectCreationFieldTitle(
                LocalizationManager.getLocaleTextByKey("main-frame.project-creation.language-field.title") + ":"
        ).getComponent(), constraint);
    }

    private void addLanguageSelectBox(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.NONE;
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.weightx = 0.6;
        constraint.insets = new Insets(50,-225,0,100);
        languageSelectBox = new JComboBox<>(new String[]{"Java"});
        dialog.add(languageSelectBox, constraint);
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
        System.out.println(projectNameTextField.getText());
        System.out.println(languageSelectBox.getSelectedItem());
        System.out.println("Create project");
    }

}
