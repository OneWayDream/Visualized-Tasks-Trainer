package ru.itis.graduationwork.application.ui.core.ide.explorer;


import ru.itis.graduationwork.application.managers.content.ImagesManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.ide.IdeFramesIconsConstants;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class ExplorerUtils {

    public static DefaultMutableTreeNode getTreeNode(TreePath path) {
        return (DefaultMutableTreeNode)(path.getLastPathComponent());
    }

    public static FileNode getFileNode(DefaultMutableTreeNode node) {
        if (node == null)
            return null;
        Object obj = node.getUserObject();
        if (obj instanceof IconData)
            obj = ((IconData) obj).getData();
        if (obj instanceof FileNode)
            return (FileNode) obj;
        else
            return null;
    }

    public static ImageIcon getImageIcon(Image image){
        ImageIcon imageIcon = ImagesManager.getImageIcon(image);
        java.awt.Image temporaryImage = imageIcon.getImage();
        java.awt.Image newImage = temporaryImage.getScaledInstance(IdeFramesIconsConstants.EXPLORER_ICON_WIDTH,
                IdeFramesIconsConstants.EXPLORER_ICON_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

}
