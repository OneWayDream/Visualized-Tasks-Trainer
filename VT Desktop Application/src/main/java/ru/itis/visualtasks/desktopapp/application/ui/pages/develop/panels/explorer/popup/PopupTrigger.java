package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.popup;

import ru.itis.visualtasks.desktopapp.application.ui.core.ide.explorer.TreePathsUtils;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class PopupTrigger extends MouseAdapter {

    private final JTree tree;

    public PopupTrigger(JTree tree){
        this.tree = tree;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
        {
            int x = e.getX();
            int y = e.getY();
            TreePath path = tree.getPathForLocation(x, y);
            if (path != null) {
                String folderPath = prepareFolderPath(path);
                ((JPopupMenu) new FileTreePopupMenu(TreePathsUtils.getFilePath(path)).getComponent()).show(tree, x, y);
            }
        }
    }

    private String prepareFolderPath(TreePath path) {
        String filePath = TreePathsUtils.getFilePath(path);
        File file = new File(filePath);
        if (!file.isDirectory()){
            filePath = filePath.substring(0, filePath.lastIndexOf(File.separatorChar));
        }
        return filePath;
    }
}
