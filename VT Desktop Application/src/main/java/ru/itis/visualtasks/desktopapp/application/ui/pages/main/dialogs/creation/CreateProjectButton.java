package ru.itis.visualtasks.desktopapp.application.ui.pages.main.dialogs.creation;

import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button;

import java.awt.event.ActionEvent;

public class CreateProjectButton extends Button {

    private final ProjectCreationDialog parentDialog;

    public CreateProjectButton(ProjectCreationDialog creationDialog){
        super();
        parentDialog = creationDialog;
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setText(LocalizationManager.getLocaleTextByKey("main-frame.project-creation.create-button.text"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        parentDialog.createProject();
    }

}
