package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.actions.creation.folder;

import ru.itis.graduationwork.application.ui.core.templates.TextField;

import java.awt.*;

public class FolderNameTextField extends TextField {

    public FolderNameTextField(){
        super();
        createTextField();
    }

    @Override
    protected void setUpTextField() {
        textField.setPreferredSize(new Dimension(300, 30));
    }

}
