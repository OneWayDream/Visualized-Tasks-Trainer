package ru.itis.graduationwork.application.ui.core.templates;

import ru.itis.graduationwork.application.managers.ConfigManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.explorer.ExplorerUtils;
import ru.itis.graduationwork.application.ui.core.explorer.FileNode;
import ru.itis.graduationwork.application.ui.core.explorer.FileWatchService;
import ru.itis.graduationwork.application.ui.core.explorer.IconData;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.io.File;

public abstract class FileTree implements Component {

    protected JTree tree;

    public FileTree(){
        tree = new JTree();
        FileWatchService.setFileTree(this);
    }

    protected void createTree(){
        adjustTree();
    }

    protected void adjustTree(){
        initTopNode();
        initContent();
    }

    protected void initTopNode(){
        ImageIcon folderImageIcon = ExplorerUtils.getImageIcon(Image.FOLDER);
        DefaultMutableTreeNode explorerTopNode = new DefaultMutableTreeNode(
                new IconData(folderImageIcon, folderImageIcon,
                        new FileNode(new File(ConfigManager.getProjectPath()))));
        tree.setModel(new DefaultTreeModel(explorerTopNode));
    }

    protected abstract void initContent();

    public void updateTreePath(TreePath treePath){
        DefaultMutableTreeNode mutableTreeNode = ExplorerUtils.getTreeNode(treePath);
        FileNode fileNode = ExplorerUtils.getFileNode(mutableTreeNode);
        mutableTreeNode.removeAllChildren();
        mutableTreeNode.add(new DefaultMutableTreeNode(true));
        fileNode.expand(mutableTreeNode);
        tree.updateUI();
    }

    @Override
    public JComponent getComponent() {
        return tree;
    }

}
