package ru.itis.graduationwork.application.ui.core;

import javax.swing.*;

public abstract class Panel implements Component {

    protected JPanel panel;

    public Panel(){
        panel = new JPanel();
    }

    protected void createPanel(){
        setPanelStyle();
        addComponents();
    }

    protected abstract void setPanelStyle();

    protected abstract void addComponents();

    @Override
    public JComponent getComponent() {
        return panel;
    }

}
