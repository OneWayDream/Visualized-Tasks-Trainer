package ru.itis.visualtasks.desktopapp.application.compilers.java;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.exceptions.execution.SolutionFileCompilationException;
import ru.itis.visualtasks.desktopapp.exceptions.execution.SolutionFileExecutingException;

import java.io.File;
import java.util.HashMap;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.*;


@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class JavaSolutionsFilesCompilerTests {

    private static final String COMPILATION_ERROR_FILES_FOLDER = "src\\test\\resources\\compilation\\java\\java_solution_classes_compiler\\compilation_error";
    private static final String INCORRECT_FILES_FOLDER = "src\\test\\resources\\compilation\\java\\java_solution_classes_compiler\\incorrect";
    private static final String CORRECT_FILES_FOLDER = "src\\test\\resources\\compilation\\java\\java_solution_classes_compiler\\correct";
    private static final String SOLUTION_FILE_NAME = "Solution.class";
    private static final String TEST_SOLUTION_FILE_NAME = "TestSolution.class";

    public static boolean SOLUTION_EXECUTION_FILE_CHECK = false;
    public static boolean TEST_SOLUTION_EXECUTION_FILE_CHECK = false;

    private void initCompilationErrorCaseClasses() {
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectPath(USER_DIRECTORY + File.separator + COMPILATION_ERROR_FILES_FOLDER)
                .language(Language.JAVA)
                .wrappersNames(new HashMap<>())
                .build();
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
    }

    private void initIncorrectCaseClasses() {
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectPath(USER_DIRECTORY + File.separator + INCORRECT_FILES_FOLDER)
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
    public void compile_correct_solution_file(){
        initCorrectCaseClasses();
        JavaSolutionFilesCompiler javaSolutionFilesCompiler = new JavaSolutionFilesCompiler();
        javaSolutionFilesCompiler.compileSolutionFile();
        checkIsFileCompiled(CORRECT_FILES_FOLDER, SOLUTION_FILE_NAME);
        deleteCompiledFile(CORRECT_FILES_FOLDER, SOLUTION_FILE_NAME);
    }

    @Test
    public void compile_incorrect_solution_file(){
        initCompilationErrorCaseClasses();
        JavaSolutionFilesCompiler javaSolutionFilesCompiler = new JavaSolutionFilesCompiler();
        Assertions.assertThrows(SolutionFileCompilationException.class, javaSolutionFilesCompiler::compileSolutionFile);
    }

    @Test
    public void compile_correct_test_solution_file(){
        initCorrectCaseClasses();
        JavaSolutionFilesCompiler javaSolutionFilesCompiler = new JavaSolutionFilesCompiler();
        javaSolutionFilesCompiler.compileTestSolutionFile();
        checkIsFileCompiled(CORRECT_FILES_FOLDER, TEST_SOLUTION_FILE_NAME);
        deleteCompiledFile(CORRECT_FILES_FOLDER, TEST_SOLUTION_FILE_NAME);
    }

    @Test
    public void compile_incorrect_test_solution_file(){
        initCompilationErrorCaseClasses();
        JavaSolutionFilesCompiler javaSolutionFilesCompiler = new JavaSolutionFilesCompiler();
        Assertions.assertThrows(SolutionFileCompilationException.class, javaSolutionFilesCompiler::compileSolutionFile);
    }

    @Test
    public void execute_correct_solution_file(){
        initCorrectCaseClasses();
        JavaSolutionFilesCompiler javaSolutionFilesCompiler = new JavaSolutionFilesCompiler();
        javaSolutionFilesCompiler.compileSolutionFile();
        javaSolutionFilesCompiler.executeSolutionFile();
        Assertions.assertTrue(SOLUTION_EXECUTION_FILE_CHECK);
        deleteCompiledFile(CORRECT_FILES_FOLDER, SOLUTION_FILE_NAME);
    }

    @Test
    public void execute_incorrect_solution_file(){
        initIncorrectCaseClasses();
        JavaSolutionFilesCompiler javaSolutionFilesCompiler = new JavaSolutionFilesCompiler();
        javaSolutionFilesCompiler.compileSolutionFile();
        Assertions.assertThrows(SolutionFileExecutingException.class, javaSolutionFilesCompiler::executeSolutionFile);
        deleteCompiledFile(INCORRECT_FILES_FOLDER, SOLUTION_FILE_NAME);
    }

    @Test
    public void execute_correct_test_solution_file(){
        initCorrectCaseClasses();
        JavaSolutionFilesCompiler javaSolutionFilesCompiler = new JavaSolutionFilesCompiler();
        javaSolutionFilesCompiler.compileTestSolutionFile();
        javaSolutionFilesCompiler.executeTestSolutionFile();
        Assertions.assertTrue(TEST_SOLUTION_EXECUTION_FILE_CHECK);
        deleteCompiledFile(CORRECT_FILES_FOLDER, TEST_SOLUTION_FILE_NAME);
    }

    @Test
    public void execute_incorrect_test_solution_file(){
        initIncorrectCaseClasses();
        JavaSolutionFilesCompiler javaSolutionFilesCompiler = new JavaSolutionFilesCompiler();
        javaSolutionFilesCompiler.compileTestSolutionFile();
        Assertions.assertThrows(SolutionFileExecutingException.class, javaSolutionFilesCompiler::executeTestSolutionFile);
        deleteCompiledFile(INCORRECT_FILES_FOLDER, TEST_SOLUTION_FILE_NAME);
    }

    private void checkIsFileCompiled(String fileFolder, String fileName){
        String compiledClassPath = USER_DIRECTORY + File.separator + fileFolder + File.separator + TARGET_DIRECTORY
                + File.separator + fileName;
        Assertions.assertTrue(new File(compiledClassPath).exists());
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
