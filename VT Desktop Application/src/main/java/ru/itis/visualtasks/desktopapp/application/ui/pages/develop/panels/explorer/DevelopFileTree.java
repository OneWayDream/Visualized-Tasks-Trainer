package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer;

import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.FileTree;
import ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.listeners.DeveloperFileExplorerMouseListener;
import ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.popup.PopupTrigger;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.explorer.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.File;

public class DevelopFileTree extends FileTree {

    public DevelopFileTree(){
        super();
        createTree();
    }

    @Override
    protected void initContent() {
        initStartTreeStructure();
        setFileTreeSettings();
        setTreeRenderer();
        setListeners();
        setPopupMenuSettings();
    }

    private void initStartTreeStructure(){
        DefaultMutableTreeNode topNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
        File[] content = getRootDirectoryContent();
        DefaultMutableTreeNode currentNode;
        for (File file : content) {
            ImageIcon imageIcon = getImageIconForFile(file);
            currentNode = new DefaultMutableTreeNode(
                    new IconData(imageIcon, imageIcon, new FileNode(file)));
            topNode.add(currentNode);
            if (file.isDirectory()){
                currentNode.add(new DefaultMutableTreeNode(true));
            }
        }
    }

    private File[] getRootDirectoryContent(){
        return new File(ConfigManager.getProjectPath()).listFiles();
    }

    private ImageIcon getImageIconForFile(File file){
        if (file.isDirectory()){
            return ExplorerUtils.getImageIcon(ru.itis.visualtasks.desktopapp.application.settings.Image.FOLDER);
        } else {
            return ExplorerUtils.getImageIcon(Image.FILE);
        }
    }

    private void setFileTreeSettings(){
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
        tree.setEditable(false);
        tree.setOpaque(false);
        tree.setBorder(BorderFactory.createEmptyBorder(20, 4, 20, 4));
        tree.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        tree.setForeground(ColorsManager.getTextColor());
        tree.setRowHeight(30);
    }

    private void setTreeRenderer(){
        tree.setCellRenderer(new IconTreeCellRenderer());
    }

    private void setListeners(){
        tree.addTreeExpansionListener(new DirectoryExpansionListener((DefaultTreeModel) tree.getModel()));
        tree.addMouseListener(new DeveloperFileExplorerMouseListener());
    }

    private void setPopupMenuSettings(){
        tree.addMouseListener(new PopupTrigger(tree));
    }

}
