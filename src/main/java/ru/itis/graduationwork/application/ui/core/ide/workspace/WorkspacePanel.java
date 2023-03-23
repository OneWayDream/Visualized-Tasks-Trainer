package ru.itis.graduationwork.application.ui.core.ide.workspace;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.project.ProjectFilesManager;
import ru.itis.graduationwork.application.managers.project.WorkspaceContentManager;
import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.settings.Mode;
import ru.itis.graduationwork.application.ui.core.ide.workspace.footer.WorkspaceFileEditorFooterPanel;
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
        panel.setOpaque(false);
        panel.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        panel.setBorder(BorderFactory.createLineBorder(ColorsManager.getBordersColor(), 3));
    }

    @Override
    protected void addComponents() {
        fillWorkspacePanel(WorkspaceContentManager.getContentPane());
    }

    private void fillWorkspacePanel(Component workspaceContent){
        if (isInformationalHeaderNeeded(workspaceContent)){
            addInformationalHeader();
        }
        panel.add(workspaceContent, BorderLayout.CENTER);
        if (isEditorFooterNeeded()){
            addEditorFooter();
        }
    }

    private boolean isInformationalHeaderNeeded(Component workspaceContent){
        return isInformationalPanel(workspaceContent) && !isStudyMode();
    }

    private boolean isInformationalPanel(Component workspaceContent) {
        return workspaceContent instanceof JEditorPane;
    }

    private boolean isStudyMode() {
        return Application.getMode() == Mode.STUDY;
    }

    private void addInformationalHeader(){
        panel.add(new WorkspaceInformationalHeaderPanel().getComponent(), BorderLayout.NORTH);
    }

    private boolean isEditorFooterNeeded(){
        return inEditorPanel() && isRunnableFile();
    }

    private boolean inEditorPanel() {
        return WorkspaceContentManager.getContentTab() == ContentTab.FILE_EDITOR;
    }

    private boolean isRunnableFile(){
        return isSolutionFile() || isTestSolutionFile();
    }

    private boolean isSolutionFile() {
        String editorFilePath = WorkspaceContentManager.getEditorFilePath();
        return editorFilePath != null && editorFilePath.equals(ProjectFilesManager.getSolutionFilePath());
    }

    private boolean isTestSolutionFile() {
        String editorFilePath = WorkspaceContentManager.getEditorFilePath();
        return editorFilePath != null && editorFilePath.equals(ProjectFilesManager.getTestSolutionFilePath());
    }

    private void addEditorFooter(){
        panel.add(new WorkspaceFileEditorFooterPanel().getComponent(), BorderLayout.SOUTH);
    }

}
