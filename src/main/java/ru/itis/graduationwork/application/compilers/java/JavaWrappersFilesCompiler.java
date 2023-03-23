package ru.itis.graduationwork.application.compilers.java;

import ru.itis.graduationwork.application.compilers.WrappersFilesCompiler;
import ru.itis.graduationwork.application.entities.project.Language;
import ru.itis.graduationwork.application.loaders.ProjectClassLoader;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.ui.core.ide.visualization.core.WrappedClass;
import ru.itis.graduationwork.exceptions.execution.WrappersMapCreationException;
import ru.itis.graduationwork.exceptions.execution.JavaFileCompilationException;
import ru.itis.graduationwork.exceptions.execution.WrappersFilesCompilationException;
import ru.itis.graduationwork.exceptions.files.WrapperFilesReadingException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class JavaWrappersFilesCompiler extends WrappersFilesCompiler {

    @Override
    public void compileWrappersFiles() {
        try{
            JavaClassesCompiler.compileFiles(getAllWrapperFiles());
        } catch (JavaFileCompilationException exception){
            throw new WrappersFilesCompilationException();
        }
    }

    private List<File> getAllWrapperFiles(){
        String folderPath = ConfigManager.getProjectPath() + File.separatorChar + WRAPPERS_FOLDER;
        try (Stream<Path> entries
                     = Files.find(Paths.get(folderPath), Integer.MAX_VALUE,
                (filePath, fileAttr) -> fileAttr.isRegularFile() && isJavaFile(String.valueOf(filePath)))
        ) {
            return entries.map(path -> new File(path.toUri())).toList();
        } catch (IOException exception) {
            throw new WrapperFilesReadingException(exception);
        }
    }

    private boolean isJavaFile(String filePath){
        return filePath.endsWith(Language.JAVA.getExtension());
    }

    @Override
    public Map<String, String> getWrappersNames(){
        List<Class<?>> wrapperClasses = getWrappersClasses();
        Map<String, String> wrappersNamesMap = new HashMap<>();
        for (Class<?> wrapperClass: wrapperClasses){
            WrappedClass wrappedClassAnnotation = wrapperClass.getAnnotation(WrappedClass.class);
            if (wrappedClassAnnotation != null){
                wrappersNamesMap.put(wrappedClassAnnotation.className(), wrapperClass.getSimpleName());
            }
        }
        return wrappersNamesMap;
    }

    private List<Class<?>> getWrappersClasses() {
        List<File> wrapperFiles = loadWrappersCompiledFiles();
        List<Class<?>> wrappersClasses = new ArrayList<>();
        ProjectClassLoader classLoader = new ProjectClassLoader();
        try{
            for (File wrapperClassFile: wrapperFiles){
                String wrapperClassFilePath = wrapperClassFile.getPath();
                String wrapperClassName = getWrapperClassName(wrapperClassFilePath);
                wrappersClasses.add(
                        Class.forName(wrapperClassName, true, classLoader)
                );
            }
        } catch (ClassNotFoundException e) {
            throw new WrappersMapCreationException(e);
        }
        return wrappersClasses;
    }

    @Override
    protected String getWrappersClassesFolder(){
        return ConfigManager.getProjectPath() + File.separator + TARGET_FOLDER + File.separator + WRAPPERS_FOLDER;
    }

    @Override
    protected boolean checkIsCompiledFile(File internalFile) {
        String filePath = internalFile.getPath();
        return filePath.endsWith(".class");
    }

    private String getWrapperClassName(String wrapperClassFilePath) {
        String intermediateString = wrapperClassFilePath.substring(getTargetFolder().length() + 1)
                .replace(File.separatorChar, '.');
        return intermediateString.substring(0, intermediateString.lastIndexOf('.'));
    }

    private String getTargetFolder(){
        return ConfigManager.getProjectPath() + File.separator + TARGET_FOLDER;
    }

}
