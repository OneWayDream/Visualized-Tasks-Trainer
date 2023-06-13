package ru.itis.visualtasks.desktopapp.application.ui.core.ide.explorer;

import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;

import javax.swing.tree.TreePath;
import java.io.File;

public class TreePathsUtils {

    public static String getFilePath(TreePath treePath){
        StringBuilder targetFilePath = new StringBuilder(ConfigManager.getProjectPath());
        for (int i = 1; i < treePath.getPathCount(); i++){
            targetFilePath.append(File.separator).append(treePath.getPathComponent(i));
        }
        return targetFilePath.toString();
    }

}
