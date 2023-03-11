package ru.itis.graduationwork.application.ui.core.ide.explorer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.settings.Mode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class FileNode {

    @Getter
    private File file;

    public boolean expand(DefaultMutableTreeNode parent) {

        DefaultMutableTreeNode flag = (DefaultMutableTreeNode) parent.getFirstChild();
        if (flag == null){
            return false;
        }

        Object userObject = flag.getUserObject();
        if (!(userObject instanceof Boolean)) {
            return false;
        }

        parent.removeAllChildren();

        File[] files = listFiles();
        if (files == null)
            return true;

        List<FileNode> fileNodeList = new ArrayList<>();

        if (Application.getMode() == Mode.DEVELOP){
            for (File file : files) {
                FileNode newNode = new FileNode(file);
                fileNodeList.add(newNode);
            }

            fileNodeList = fileNodeList.stream().sorted(FileNode::compareTo).toList();
        } else {
            Arrays.stream(listFiles())
                    .filter(file1 -> file1.getName().equals("Solution.java"))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
        }

        for (FileNode fileNode: fileNodeList){
            ImageIcon imageIcon = getImageIconForFile(fileNode.getFile());
            IconData iconData = new IconData(imageIcon, imageIcon, fileNode);
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(iconData);
            parent.add(node);
            if (fileNode.hasSubFiles()){
                node.add(new DefaultMutableTreeNode(true));

            }
        }

        return true;
    }

    private ImageIcon getImageIconForFile(File file){
        if (file.isDirectory()){
            return ExplorerUtils.getImageIcon(Image.FOLDER);
        } else {
            return ExplorerUtils.getImageIcon(Image.FILE);
        }
    }

    public boolean hasSubFiles() {
        File[] files = listFiles();
        return files != null;
    }

    public int compareTo(FileNode toCompare) {
        return file.getName().compareToIgnoreCase(
                toCompare.getFile().getName());
    }

    protected File[] listFiles() {
        if (!file.isDirectory())
            return null;
        try {
            return file.listFiles();
        } catch (Exception ex) {
            ExceptionsManager.handleDirectoryExploreException();
            return null;
        }
    }

    @Override
    public String toString() {
        return file.getName();
    }

}
