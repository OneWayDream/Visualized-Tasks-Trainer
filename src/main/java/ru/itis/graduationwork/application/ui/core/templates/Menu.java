package ru.itis.graduationwork.application.ui.core.templates;

import javax.swing.*;

public abstract class Menu implements Component{

    protected final JMenu menu;

    protected Menu(){
        menu = new JMenu();
        initMenu();
    }

    protected abstract void initMenu();

    @Override
    public JComponent getComponent() {
        return menu;
    }

}
