package ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.editor;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.ScrollPane;

public class FileEditorScrollPane extends ScrollPane {

    private FileEditor fileEditor;

    public FileEditorScrollPane(){
        super();
        createScrollPane();
    }

    @Override
    protected void setScrollPaneStyle() {
        scrollPane.setOpaque(true);
        scrollPane.setBackground(ColorsManager.getPanelBackgroundColor());
        scrollPane.getViewport().setOpaque(false);
    }

    @Override
    protected void addComponents() {
        fileEditor = new FileEditor();
        scrollPane.getViewport().setView(fileEditor.getComponent());
    }

    public void saveChanges(){
        fileEditor.saveChanges();
    }
}
