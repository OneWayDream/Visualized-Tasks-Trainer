package ru.itis.visualtasks.desktopapp.application.ui.pages.study.panels.workspace.notselected;

import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Panel;

import java.awt.*;

public class NotSelectedFilePanel extends Panel {

    public NotSelectedFilePanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new BorderLayout());
    }

    @Override
    protected void addComponents() {
        panel.add(new NotSelectedFileLabel().getComponent(), BorderLayout.CENTER);
    }

}
