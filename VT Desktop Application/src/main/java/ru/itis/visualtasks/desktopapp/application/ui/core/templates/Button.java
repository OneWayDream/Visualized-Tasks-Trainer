package ru.itis.visualtasks.desktopapp.application.ui.core.templates;

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

    protected void disableDefaultButtonStyle(){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    protected abstract void setButtonStyle();

    @Override
    public JComponent getComponent() {
        return button;
    }

}
