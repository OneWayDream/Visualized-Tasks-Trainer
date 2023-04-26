package ru.itis.visualtasks.desktopapp.application.ui.pages.study.panels.explorer;

import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.FileTree;
import ru.itis.visualtasks.desktopapp.application.ui.pages.study.panels.explorer.listeners.StudentFileExplorerMouseListener;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.explorer.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.File;

public class StudyFileTree extends FileTree {

    public StudyFileTree(){
        super();
        createTree();
    }

    @Override
    protected void initContent() {
        initStartTreeStructure();
        setFileTreeSettings();
        setTreeRenderer();
        setListeners();
    }

    private void initStartTreeStructure(){
        DefaultMutableTreeNode topNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
        File solutionFile = getSolutionFile();
        ImageIcon solutionImageIcon = ExplorerUtils.getImageIcon(Image.FILE);
        DefaultMutableTreeNode solutionFileNode = new DefaultMutableTreeNode(
                new IconData(solutionImageIcon, solutionImageIcon, new FileNode(solutionFile)));
        topNode.add(solutionFileNode);
    }

    private File getSolutionFile(){
        return new File(ProjectFilesManager.getSolutionFilePath());
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
        tree.addMouseListener(new StudentFileExplorerMouseListener());
    }

}
