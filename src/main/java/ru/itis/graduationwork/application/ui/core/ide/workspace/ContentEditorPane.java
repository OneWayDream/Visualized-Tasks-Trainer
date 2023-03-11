package ru.itis.graduationwork.application.ui.core.ide.workspace;

import ru.itis.graduationwork.application.ui.core.templates.EditorPane;

import java.awt.*;

public class ContentEditorPane extends EditorPane {

    private final String content;

    public ContentEditorPane(String content){
        super();
        this.content = content;
        createEditorPane();
    }

    @Override
    protected void createEditorPane() {
        editorPane.setContentType("text/html");
        editorPane.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        editorPane.setText(content);
        editorPane.setEditable(false);
        editorPane.setOpaque(false);
    }

}
