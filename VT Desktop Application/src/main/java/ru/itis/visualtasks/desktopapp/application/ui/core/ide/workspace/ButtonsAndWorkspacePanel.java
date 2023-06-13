package ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace;

import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Panel;

import java.awt.*;

public class ButtonsAndWorkspacePanel extends Panel {

    private WorkspacePanel workspacePanel;

    public ButtonsAndWorkspacePanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());

    }

    @Override
    protected void addComponents() {
        panel.add(new ButtonsColumnPanel().getComponent(), BorderLayout.LINE_START);
        addWorkspacePanel();
    }

    private void addWorkspacePanel(){
        workspacePanel = new WorkspacePanel();
        panel.add(workspacePanel.getComponent(), BorderLayout.CENTER);
    }

    public void updateWorkspaceContent() {
        panel.remove(workspacePanel.getComponent());
        addWorkspacePanel();
        panel.validate();
        panel.repaint();
    }
}
