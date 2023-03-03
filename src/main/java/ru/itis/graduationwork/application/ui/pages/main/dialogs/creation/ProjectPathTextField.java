package ru.itis.graduationwork.application.ui.pages.main.dialogs.creation;

import ru.itis.graduationwork.application.ui.core.templates.TextField;

import java.awt.*;

public class ProjectPathTextField extends TextField {

    public ProjectPathTextField(){
        super();
        createTextField();
    }

    @Override
    protected void setUpTextField() {
        textField.setPreferredSize(new Dimension(500, 30));
    }

    public void setText(String text){
        textField.setText(text);
    }

}
