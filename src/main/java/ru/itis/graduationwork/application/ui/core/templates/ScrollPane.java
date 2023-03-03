package ru.itis.graduationwork.application.ui.core.templates;

import javax.swing.*;

public abstract class ScrollPane implements Component {

    protected JScrollPane scrollPane;

    public ScrollPane(){
        scrollPane = new JScrollPane();
    }

    protected void createScrollPane(){
        setScrollPaneStyle();
        addComponents();
    }

    protected abstract void setScrollPaneStyle();

    protected abstract void addComponents();

    @Override
    public JComponent getComponent() {
        return scrollPane;
    }

}
