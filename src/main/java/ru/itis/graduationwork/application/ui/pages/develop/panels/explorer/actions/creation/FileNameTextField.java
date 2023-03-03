package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.actions.creation;

import ru.itis.graduationwork.application.ui.core.templates.TextField;

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
