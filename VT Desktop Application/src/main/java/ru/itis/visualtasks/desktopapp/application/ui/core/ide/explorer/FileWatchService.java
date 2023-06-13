package ru.itis.visualtasks.desktopapp.application.ui.core.ide.explorer;

import lombok.Setter;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.FileTree;
import ru.itis.visualtasks.desktopapp.exceptions.explorer.MonitoringRegistrationException;
import ru.itis.visualtasks.desktopapp.exceptions.explorer.MonitoringThreadException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileWatchServiceInitializationException;

import javax.swing.tree.TreePath;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

public class FileWatchService {

    private static WatchService watchService;
    private static final Map<String, TreePath> treePathMap = new HashMap<>();
    @Setter
    private static FileTree fileTree;

    static {
        initWatchService();
        startMonitoringProcess();
    }

    private static void initWatchService(){
        try{
            watchService = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            throw new FileWatchServiceInitializationException(e);
        }try{
            watchService = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            throw new FileWatchServiceInitializationException(e);
        }
    }

    private static void startMonitoringProcess(){
        Thread monitoringThread = new Thread(new MonitoringRunnable());
        monitoringThread.setDaemon(true);
        try{
            monitoringThread.start();
        } catch (MonitoringThreadException exception){
            exception.handle();
            startMonitoringProcess();
        }
    }

    public static void addTreePathToMonitoring(TreePath treePath){
        String folderPath = TreePathsUtils.getFilePath(treePath);
        treePathMap.put(folderPath, treePath);
        try{
            Paths.get(folderPath).register(watchService, ENTRY_CREATE, ENTRY_DELETE);
        } catch (IOException e) {
            throw new MonitoringRegistrationException(e);
        }
    }

    public static void clearMonitoringTreePaths(){
        treePathMap.clear();
    }

    public static void removeTreePathSetFromMonitoring(TreePath treePath){
        treePathMap.remove(TreePathsUtils.getFilePath(treePath));
    }

    public static void updatePath(String path){
        String changedFolder = path.substring(0, path.lastIndexOf(File.separator));
        if (treePathMap.containsKey(path)){
            fileTree.updateTreePath(treePathMap.get(changedFolder));
        }
    }

    private static class MonitoringRunnable implements Runnable{

        @Override
        public void run() {
            while (true){
                try{
                    WatchKey key = watchService.take();
                    for (WatchEvent<?> event: key.pollEvents()){
                        File changedFile = Paths.get(ConfigManager.getProjectPath()).resolve((Path) event.context()).toFile();
                        String changedFolder = changedFile.getPath();
                        changedFolder = changedFolder.substring(0, changedFolder.lastIndexOf(File.separator));
                        fileTree.updateTreePath(treePathMap.get(changedFolder));
                    }
                    key.reset();
                } catch (InterruptedException e) {
                    throw new MonitoringThreadException(e);
                }
            }
        }
    }

}
