package ru.itis.graduationwork.application.ui.pages.develop.panels.workspace;

import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.WorkspaceContentManager;
import ru.itis.graduationwork.application.ui.core.templates.Panel;
import ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.header.WorkspaceInformationalHeaderPanel;

import javax.swing.*;
import java.awt.*;

public class WorkspacePanel extends Panel {

    public WorkspacePanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new BorderLayout());
        panel.setBackground(ColorsManager.getPanelBackgroundColor());
        panel.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        panel.setBorder(BorderFactory.createLineBorder(ColorsManager.getBordersColor(), 3));
    }

    @Override
    protected void addComponents() {
        fillWorkspacePanel(WorkspaceContentManager.getContentPane());
    }

    private void fillWorkspacePanel(Component workspaceContent){
        if (workspaceContent instanceof JEditorPane){
            addHeader();
        }
        panel.add(workspaceContent, BorderLayout.CENTER);
    }

    private void addHeader(){
        panel.add(new WorkspaceInformationalHeaderPanel().getComponent(), BorderLayout.NORTH);
    }

}
