package ru.itis.graduationwork.application.managers.project;

import ru.itis.graduationwork.application.loaders.ProjectClassLoader;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.files.FilesManager;
import ru.itis.graduationwork.application.managers.utils.CompilationErrorsManager;
import ru.itis.graduationwork.exceptions.files.FileNotFoundException;

import javax.tools.*;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaClassesCompiler {

    private static final JavaCompiler JAVA_COMPILER;
    private static final StandardJavaFileManager STANDARD_JAVA_FILE_MANAGER;

    static {
        JAVA_COMPILER = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
        STANDARD_JAVA_FILE_MANAGER = JAVA_COMPILER.getStandardFileManager(diagnosticCollector, null, null);
    }

    public static void compileFiles(List<File> filesToCompile){
        for (File file: filesToCompile){
            String filePath = file.getPath();
            Iterable<? extends JavaFileObject> compilationData = STANDARD_JAVA_FILE_MANAGER.getJavaFileObjectsFromFiles(List.of(file));
            compileJavaClass(filePath, compilationData);
        }
    }

    public static void compileFile(String filePath){
        Iterable<? extends JavaFileObject> compilationData = getCompilationData(filePath);
        compileJavaClass(filePath, compilationData);
    }

    private static Iterable<? extends JavaFileObject> getCompilationData(String filePath){
        File fileToCompile = loadJavaFile(filePath);
        return STANDARD_JAVA_FILE_MANAGER.getJavaFileObjectsFromFiles(List.of(fileToCompile));
    }

    private static File loadJavaFile(String filePath){
        if (!FilesManager.checkIsFileExist(filePath)){
            throw new FileNotFoundException();
        }
        return new File(filePath);
    }

    private static void compileJavaClass(String filePath, Iterable<? extends JavaFileObject> compilationData){
        Writer writer = new StringWriter();
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
        boolean success = JAVA_COMPILER.getTask(writer, STANDARD_JAVA_FILE_MANAGER, diagnosticCollector, getOptionsList(),
                null, compilationData).call();
        if (!success) {
            List<Diagnostic<? extends JavaFileObject>> diagnostics = diagnosticCollector.getDiagnostics();
            CompilationErrorsManager.handleDiagnostics(filePath, diagnostics);
        }
    }

    private static String getTargetFolderPath(){
        return ConfigManager.getProjectPath() + File.separatorChar + ProjectClassLoader.TARGET_FOLDER;
    }

    private static List<String> getOptionsList(){
        List<String> optionList = new ArrayList<>();
        optionList.addAll(Arrays.asList("-classpath", ConfigManager.getProjectPath() + ";" + System.getProperty("java.class.path")));
        optionList.addAll(Arrays.asList("-d", getTargetFolderPath()));
        return optionList;
    }

}
