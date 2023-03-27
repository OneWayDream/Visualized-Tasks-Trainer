package ru.itis.visualtasks.desktopapp.application.ui.core.ide.explorer;

import lombok.AllArgsConstructor;
import ru.itis.visualtasks.desktopapp.exceptions.explorer.MonitoringRegistrationException;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

@AllArgsConstructor
public class DirectoryExpansionListener implements TreeExpansionListener {

    private DefaultTreeModel model;

    @Override
    public void treeExpanded(TreeExpansionEvent event) {
        DefaultMutableTreeNode mutableTreeNode = ExplorerUtils.getTreeNode(event.getPath());
        FileNode fileNode = ExplorerUtils.getFileNode(mutableTreeNode);

        Thread reloadRunner = new Thread(() -> {
            if (fileNode != null && fileNode.expand(mutableTreeNode)) {
                Runnable runnable = () -> model.reload(mutableTreeNode);
                SwingUtilities.invokeLater(runnable);
            }
        });
        reloadRunner.start();
        try{
            FileWatchService.addTreePathToMonitoring(event.getPath());
        } catch (MonitoringRegistrationException exception){
            exception.handle();
        }
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {
        FileWatchService.removeTreePathSetFromMonitoring(event.getPath());
    }

}
