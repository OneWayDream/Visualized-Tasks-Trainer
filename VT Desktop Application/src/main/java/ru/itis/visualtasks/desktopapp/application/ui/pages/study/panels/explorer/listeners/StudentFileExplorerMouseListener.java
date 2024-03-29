package ru.itis.visualtasks.desktopapp.application.ui.pages.study.panels.explorer.listeners;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.WorkspaceContentManager;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.explorer.TreePathsUtils;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.ContentTab;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class StudentFileExplorerMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(event)) {
            TreePath treePath = ((JTree) event.getSource()).getSelectionModel().getSelectionPath();
            if (treePath != null){
                String targetFilePath = TreePathsUtils.getFilePath(treePath);
                if (!isFolder(targetFilePath)){
                    if (isStudyMode() && isEditorFilePathIsNull()){
                        WorkspaceContentManager.setEditorContent(ProjectFilesManager.getSolutionFilePath());
                    } else {
                        WorkspaceContentManager.changeWorkspaceContent(ContentTab.FILE_EDITOR);
                    }
                }
            }
        }
    }

    private boolean isFolder(String filePath){
        return new File(filePath).isDirectory();
    }

    private boolean isStudyMode(){
        return Application.getMode() == Mode.STUDY;
    }

    private boolean isEditorFilePathIsNull(){
        return WorkspaceContentManager.getEditorFilePath() == null;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
