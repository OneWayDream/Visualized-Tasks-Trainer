package ru.itis.graduationwork.application.ui.core.templates;

import javax.swing.*;

public abstract class TextField implements Component {

    protected JTextField textField;

    protected TextField(){
        textField = new JTextField();
    }

    protected void createTextField(){
        setUpTextField();
    }

    protected abstract void setUpTextField();

    @Override
    public JComponent getComponent() {
        return textField;
    }

    public String getText(){
        return textField.getText();
    }

}
