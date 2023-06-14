package ru.itis.visualtasks.desktopapp.application.compilers.java;

import ru.itis.visualtasks.desktopapp.application.loaders.ProjectClassLoader;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.utils.CompilationErrorsManager;
import ru.itis.visualtasks.desktopapp.exceptions.execution.JavaFileCompilationException;
import ru.itis.visualtasks.desktopapp.exceptions.execution.SolutionFileReadingException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileNotFoundException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileReadingException;

import javax.tools.*;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaClassesCompiler {

    private static final String SOLUTION_FILE_NAME = "Solution.java";
    private static final String TEST_SOLUTION_FILE_NAME = "TestSolution.java";
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
        Iterable<? extends JavaFileObject> iterableResult = STANDARD_JAVA_FILE_MANAGER.getJavaFileObjectsFromFiles(List.of(fileToCompile));
        if (checkIfSolutionFile(filePath)){
            iterableResult = prepareSolutionFile(iterableResult, filePath);
        }
        return iterableResult;
    }

    private static Iterable<? extends JavaFileObject> prepareSolutionFile(
            Iterable<? extends JavaFileObject> iterableResult, String filePath) {
        try {
            JavaFileObject javaFileObject = iterableResult.iterator().next();
            return List.of(
                    new SolutionJavaSourceFromString(
                            javaFileObject.toUri(), javaFileObject.getKind(),FilesManager.readFileAsString(filePath)
                    )
            );
        } catch (FileReadingException exception){
            throw new SolutionFileReadingException(exception);
        }
    }

    private static File loadJavaFile(String filePath){
        if (!FilesManager.checkIsFileExist(filePath)){
            throw new FileNotFoundException();
        }
        return new File(filePath);
    }

    private static boolean checkIfSolutionFile(String filePath) {
        return filePath.endsWith(SOLUTION_FILE_NAME) || filePath.endsWith(TEST_SOLUTION_FILE_NAME);
    }


    private static void compileJavaClass(String filePath, Iterable<? extends JavaFileObject> compilationData){
        Writer writer = new StringWriter();
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
        boolean success = JAVA_COMPILER.getTask(writer, STANDARD_JAVA_FILE_MANAGER, diagnosticCollector, getOptionsList(),
                null, compilationData).call();
        if (!success) {
            List<Diagnostic<? extends JavaFileObject>> diagnostics = diagnosticCollector.getDiagnostics();
            CompilationErrorsManager.handleDiagnostics(filePath, diagnostics);
            throw new JavaFileCompilationException();
        }
    }

    private static String getTargetFolderPath(){
        return ConfigManager.getProjectPath() + File.separatorChar + ProjectClassLoader.TARGET_FOLDER;
    }

    private static List<String> getOptionsList(){
        List<String> optionList = new ArrayList<>();
        StringBuilder classPathVar = new StringBuilder();
        classPathVar.append(ConfigManager.getProjectPath()).append(";")
                        .append(System.getProperty("java.class.path")).append(";");
        String jarPaths = getJarPaths();
        if (jarPaths.length() != 0) classPathVar.append(jarPaths);
        optionList.addAll(Arrays.asList("-classpath", classPathVar.toString()));
        optionList.addAll(Arrays.asList("-d", getTargetFolderPath()));
        return optionList;
    }

    private static String getJarPaths(){
        File file = new File(ConfigManager.getProjectPath() + File.separator + "libs");
        StringBuilder imports = new StringBuilder();
        if (file.exists()){
            for (File f: file.listFiles()){
                imports.append(f.getPath()).append(";");
            }
        }
        return imports.toString();
    }

}
