package ru.itis.visualtasks.desktopapp.application.compilers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class WrappersFilesCompiler {

    protected static final String WRAPPERS_FOLDER = "wrappers";
    protected static final String TARGET_FOLDER = "target";

    public abstract void compileWrappersFiles();
    public abstract Map<String, String> getWrappersNames();

    protected List<File> loadWrappersCompiledFiles(){
        String wrappersClassesFolder = getWrappersClassesFolder();
        return loadAllClassFilesFromFolder(wrappersClassesFolder);
    }

    protected abstract String getWrappersClassesFolder();

    private List<File> loadAllClassFilesFromFolder(String wrappersClassesFolderPath){
        File wrappersClassesFolder = new File(wrappersClassesFolderPath);
        List<File> resultFiles = new ArrayList<>();
        if (wrappersClassesFolder.listFiles() != null){
            List<File> internalFiles = List.of(wrappersClassesFolder.listFiles());
            for (File internalFile : internalFiles){
                if (internalFile.isDirectory()){
                    resultFiles.addAll(loadAllClassFilesFromFolder(internalFile.getPath()));
                } else if (checkIsCompiledFile(internalFile)){
                    resultFiles.add(internalFile);
                }
            }
        }
        return resultFiles;
    }

    protected abstract boolean checkIsCompiledFile(File internalFile);

}
