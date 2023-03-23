package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer;

import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.ui.core.templates.ScrollPane;

public class DevelopFileScrollPane extends ScrollPane {

    public DevelopFileScrollPane(){
        super();
        createScrollPane();
    }

    @Override
    protected void setScrollPaneStyle() {
        scrollPane.setOpaque(false);
        scrollPane.setBackground(ColorsManager.getPanelBackgroundColor());
        scrollPane.getViewport().setOpaque(true);
    }

    @Override
    protected void addComponents() {
        scrollPane.getViewport().add(new DevelopFileTree().getComponent());
    }

}
