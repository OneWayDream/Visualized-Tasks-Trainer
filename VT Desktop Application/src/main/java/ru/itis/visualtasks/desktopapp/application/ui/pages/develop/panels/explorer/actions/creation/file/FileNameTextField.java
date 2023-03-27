package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.actions.creation.file;

import ru.itis.visualtasks.desktopapp.application.ui.core.templates.TextField;

import java.awt.*;

public class FileNameTextField extends TextField {

    public FileNameTextField(){
        super();
        createTextField();
    }

    @Override
    protected void setUpTextField() {
        textField.setPreferredSize(new Dimension(300, 30));
    }


}
