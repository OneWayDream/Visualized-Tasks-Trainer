package ru.itis.graduationwork.application.ui.core;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class Button implements Component, ActionListener {

    protected JButton button;

    protected Button(){
        button = new JButton();
    }

    protected Button(JButton button){
        this.button = button;
    }

    protected void createButton(){
        setButtonStyle();
        button.addActionListener(this);
    }

    protected abstract void setButtonStyle();

    @Override
    public JComponent getComponent() {
        return button;
    }

}