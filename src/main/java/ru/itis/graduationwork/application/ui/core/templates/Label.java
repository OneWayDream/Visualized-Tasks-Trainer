package ru.itis.graduationwork.application.ui.core.templates;

import javax.swing.*;

public abstract class Label implements Component{

    protected JLabel label;

    protected Label(){
        label = new JLabel();
    }

    protected void createLabel(){
        setUpLabel();
    }

    protected abstract void setUpLabel();

    @Override
    public JComponent getComponent() {
        return label;
    }

}
