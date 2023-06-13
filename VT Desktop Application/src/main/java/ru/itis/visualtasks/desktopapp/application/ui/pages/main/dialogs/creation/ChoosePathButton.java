package ru.itis.visualtasks.desktopapp.application.ui.pages.main.dialogs.creation;

import ru.itis.visualtasks.desktopapp.application.managers.content.ImagesManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ChoosePathButton extends Button {

    private final ProjectPathTextField projectPathTextField;
    private final ProjectNameTextField projectNameTextField;

    private final NewProjectPathChooser pathChooser;

    public ChoosePathButton(ProjectPathTextField projectPathTextField, ProjectNameTextField projectNameTextField){
        super();
        this.projectPathTextField = projectPathTextField;
        this.projectNameTextField = projectNameTextField;
        pathChooser = new NewProjectPathChooser();
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        setImageIcon();
    }

    private void setImageIcon(){
        java.awt.Image imageIcon = ImagesManager.getImageIcon(Image.FOLDER).getImage();
        java.awt.Image resizedImage = imageIcon.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(resizedImage));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String projectName = projectNameTextField.getText();
        pathChooser.execute();
        if (pathChooser.getSelectedDirectory() != null){
            projectPathTextField.setText(pathChooser.getSelectedDirectory() + File.separator + projectName);
        }
    }

}
