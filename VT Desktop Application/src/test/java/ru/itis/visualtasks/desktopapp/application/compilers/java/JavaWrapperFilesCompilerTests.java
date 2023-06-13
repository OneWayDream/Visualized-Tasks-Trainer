package ru.itis.visualtasks.desktopapp.application.compilers.java;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.exceptions.execution.WrappersFilesCompilationException;

import java.io.File;
import java.util.HashMap;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.TARGET_DIRECTORY;
import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;


@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class JavaWrapperFilesCompilerTests {

    private static final String INCORRECT_FILES_FOLDER = "src\\test\\resources\\compilation\\java\\java_wrapper_files_compiler\\incorrect";
    private static final String NO_WRAPPERS_FILES_FOLDER = "src\\test\\resources\\compilation\\java\\java_wrapper_files_compiler\\no_wrappers";
    private static final String CORRECT_FILES_FOLDER = "src\\test\\resources\\compilation\\java\\java_wrapper_files_compiler\\correct";
    private static final String COMPILED_WRAPPER_NAME = "wrappers\\Wrapper.class";

    private void initIncorrectCaseClasses() {
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectPath(USER_DIRECTORY + File.separator + INCORRECT_FILES_FOLDER)
                .language(Language.JAVA)
                .wrappersNames(new HashMap<>())
                .build();
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
    }

    private void initNoWrappersCaseClasses() {
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectPath(USER_DIRECTORY + File.separator + NO_WRAPPERS_FILES_FOLDER)
                .language(Language.JAVA)
                .wrappersNames(new HashMap<>())
                .build();
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
    }

    private void initCorrectCaseClasses() {
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectPath(USER_DIRECTORY + File.separator + CORRECT_FILES_FOLDER)
                .language(Language.JAVA)
                .wrappersNames(new HashMap<>())
                .build();
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
    }

    @Test
    public void compile_correct_wrapper_files(){
        initCorrectCaseClasses();
        JavaWrapperFilesCompiler javaWrapperFilesCompiler = new JavaWrapperFilesCompiler();
        javaWrapperFilesCompiler.compileWrappersFiles();
        checkIsFileCompiled(CORRECT_FILES_FOLDER, COMPILED_WRAPPER_NAME);
        deleteCompiledFile(CORRECT_FILES_FOLDER, COMPILED_WRAPPER_NAME);
    }

    private void checkIsFileCompiled(String fileFolder, String fileName){
        String compiledClassPath = USER_DIRECTORY + File.separator + fileFolder + File.separator + TARGET_DIRECTORY
                + File.separator + fileName;
        Assertions.assertTrue(new File(compiledClassPath).exists());
    }

    @Test
    public void compile_incorrect_wrapper_files(){
        initIncorrectCaseClasses();
        JavaWrapperFilesCompiler javaWrapperFilesCompiler = new JavaWrapperFilesCompiler();
        Assertions.assertThrows(WrappersFilesCompilationException.class, javaWrapperFilesCompiler::compileWrappersFiles);
    }

    @Test
    public void get_void_wrappers_names(){
        initNoWrappersCaseClasses();
        JavaWrapperFilesCompiler javaWrapperFilesCompiler = new JavaWrapperFilesCompiler();
        javaWrapperFilesCompiler.compileWrappersFiles();
        Assertions.assertTrue(javaWrapperFilesCompiler.getWrappersNames().isEmpty());
    }

    @Test
    public void get_not_empty_wrappers_names(){
        initCorrectCaseClasses();
        JavaWrapperFilesCompiler javaWrapperFilesCompiler = new JavaWrapperFilesCompiler();
        javaWrapperFilesCompiler.compileWrappersFiles();
        Assertions.assertEquals("Wrapper", javaWrapperFilesCompiler.getWrappersNames().get("WrappedClass"));
        deleteCompiledFile(CORRECT_FILES_FOLDER, COMPILED_WRAPPER_NAME);
    }

    private static void deleteCompiledFile(String folderPath, String compiledFileName) {
        try{
            new File(USER_DIRECTORY  + File.separator + folderPath + File.separator + TARGET_DIRECTORY
                    + File.separator + compiledFileName).delete();
        } catch (Exception exception){
            //ignore
        }
    }

    @AfterAll
    public static void resetServeClasses() {
        ProjectFilesManager.reset();
        ConfigManager.setConfig(null);
    }

}
