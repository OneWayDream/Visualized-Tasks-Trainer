package ru.itis.graduationwork.application.ui.core.explorer;

import lombok.AllArgsConstructor;
import ru.itis.graduationwork.application.managers.ExceptionsManager;
import ru.itis.graduationwork.exceptions.explorer.MonitoringRegistrationException;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.concurrent.TimeUnit;

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
            ExceptionsManager.addDelayedException(
                    ExceptionsManager::handleMonitoringRegistrationException, 200, TimeUnit.MILLISECONDS
            );
        }
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {
        FileWatchService.removeTreePathSetFromMonitoring(event.getPath());
    }

}
