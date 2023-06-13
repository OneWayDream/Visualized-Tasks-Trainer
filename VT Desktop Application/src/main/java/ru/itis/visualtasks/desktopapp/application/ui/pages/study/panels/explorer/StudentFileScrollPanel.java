package ru.itis.visualtasks.desktopapp.application.ui.pages.study.panels.explorer;

import ru.itis.visualtasks.desktopapp.application.ui.core.templates.ScrollPane;

public class StudentFileScrollPanel extends ScrollPane {

    public StudentFileScrollPanel(){
        super();
        createScrollPane();
    }

    @Override
    protected void setScrollPaneStyle() {
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(true);
    }

    @Override
    protected void addComponents() {
        scrollPane.getViewport().add(new StudyFileTree().getComponent());
    }

}
