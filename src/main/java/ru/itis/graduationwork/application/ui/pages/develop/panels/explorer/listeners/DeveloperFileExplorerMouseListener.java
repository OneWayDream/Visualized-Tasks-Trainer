package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.listeners;

import ru.itis.graduationwork.application.managers.WorkspaceContentManager;
import ru.itis.graduationwork.application.ui.core.explorer.TreePathsUtils;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class DeveloperFileExplorerMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(event)) {
            TreePath treePath = ((JTree) event.getSource()).getSelectionModel().getSelectionPath();
            if (treePath != null){
                String targetFilePath = TreePathsUtils.getFilePath(treePath);
                if (!isFolder(targetFilePath)){
                    WorkspaceContentManager.setEditorContent(targetFilePath);
                }
            }
        }
    }

    private boolean isFolder(String filePath){
        return new File(filePath).isDirectory();
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
