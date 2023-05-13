package ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.ScrollPane;

public class ContentScrollPane extends ScrollPane {

    private final ContentEditorPane contentEditorPane;

    public ContentScrollPane(ContentEditorPane contentEditorPane){
        super();
        this.contentEditorPane = contentEditorPane;
        createScrollPane();
    }

    @Override
    protected void setScrollPaneStyle() {
        scrollPane.setOpaque(true);
        scrollPane.setBackground(ColorsManager.getPanelBackgroundColor());
        scrollPane.getViewport().setOpaque(true);
    }

    @Override
    protected void addComponents() {
        scrollPane.getViewport().setView(contentEditorPane.getComponent());
    }

}
