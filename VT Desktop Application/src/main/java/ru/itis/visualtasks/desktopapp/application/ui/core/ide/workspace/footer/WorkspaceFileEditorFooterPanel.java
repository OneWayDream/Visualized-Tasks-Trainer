package ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.footer;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Panel;

import javax.swing.*;

public class WorkspaceFileEditorFooterPanel extends Panel {

    public WorkspaceFileEditorFooterPanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, ColorsManager.getBordersColor()));
    }

    @Override
    protected void addComponents() {
        panel.add(new RunFileButton().getComponent());
    }

}
