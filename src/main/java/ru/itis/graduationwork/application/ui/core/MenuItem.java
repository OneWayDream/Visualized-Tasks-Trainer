package ru.itis.graduationwork.application.ui.core;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class MenuItem implements Component, ActionListener {

    protected final JMenuItem menuItem;

    protected MenuItem(){
        menuItem = new JMenuItem();
        initMenuItem();
        menuItem.addActionListener(this);
    }

    protected abstract void initMenuItem();

    @Override
    public JComponent getComponent() {
        return menuItem;
    }

}
