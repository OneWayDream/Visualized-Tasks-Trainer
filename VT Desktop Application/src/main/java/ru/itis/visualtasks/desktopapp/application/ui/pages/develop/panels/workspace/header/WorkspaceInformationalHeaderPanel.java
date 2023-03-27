package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.workspace.header;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Panel;

import javax.swing.*;
import java.awt.*;

public class WorkspaceInformationalHeaderPanel extends Panel {

    public WorkspaceInformationalHeaderPanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ColorsManager.getBordersColor()));
    }

    @Override
    protected void addComponents() {
        panel.add(new ChangeContentFileButton().getComponent());
        panel.add(Box.createRigidArea(new Dimension(10,0)));
        panel.add(new UpdateContentFileButton().getComponent());
        panel.add(Box.createRigidArea(new Dimension(10,0)));
        panel.add(new EditContentFileButton().getComponent());
    }

}
