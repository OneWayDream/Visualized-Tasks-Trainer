package ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.WorkspaceContentManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.footer.WorkspaceFileEditorFooterPanel;
import ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.workspace.header.WorkspaceInformationalHeaderPanel;

import javax.swing.*;
import java.awt.*;

public class WorkspacePanel extends ru.itis.visualtasks.desktopapp.application.ui.core.templates.Panel {

    public WorkspacePanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new BorderLayout());
        panel.setBackground(ColorsManager.getWorkspaceBackgroundColor());
        panel.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        panel.setBorder(BorderFactory.createLineBorder(ColorsManager.getBordersColor(), 3));
    }

    @Override
    protected void addComponents() {
        fillWorkspacePanel(WorkspaceContentManager.getContentPane());
    }

    private void fillWorkspacePanel(Component workspaceContent){
        if (isInformationalHeaderNeeded()){
            addInformationalHeader();
        }
        panel.add(workspaceContent, BorderLayout.CENTER);
        if (isEditorFooterNeeded()){
            addEditorFooter();
        }
    }

    private boolean isInformationalHeaderNeeded(){
        return isInformationalPanel() && !isStudyMode();
    }

    private boolean isInformationalPanel() {
        return WorkspaceContentManager.getContentTab() != ContentTab.FILE_EDITOR;
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
