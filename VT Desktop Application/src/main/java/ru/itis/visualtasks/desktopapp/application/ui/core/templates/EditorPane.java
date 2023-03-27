package ru.itis.visualtasks.desktopapp.application.ui.core.templates;

import javax.swing.*;

public abstract class EditorPane implements Component {

    protected JEditorPane editorPane;

    public EditorPane(){
        editorPane = new JEditorPane();
    }

    protected abstract void createEditorPane();

    @Override
    public JComponent getComponent() {
        return editorPane;
    }

}
