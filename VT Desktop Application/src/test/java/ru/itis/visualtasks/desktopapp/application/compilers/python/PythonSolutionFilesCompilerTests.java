package ru.itis.visualtasks.desktopapp.application.compilers.python;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.exceptions.execution.SolutionFileExecutingException;

import java.io.File;
import java.util.HashMap;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.TARGET_DIRECTORY;
import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class PythonSolutionFilesCompilerTests {

    private static final String INCORRECT_FILES_FOLDER = "src\\test\\resources\\compilation\\python\\python_solution_files_compiler\\incorrect";
    private static final String CORRECT_FILES_FOLDER = "src\\test\\resources\\compilation\\python\\python_solution_files_compiler\\correct";
    private static final String SOLUTION_FILE_NAME = "solution.py";
    private static final String TEST_SOLUTION_FILE_NAME = "test_solution.py";
    private static final String VISUALIZATION_STEPS_FILE_NAME = "visualization-actions.json";

    private void initIncorrectCaseClasses() {
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectPath(USER_DIRECTORY + File.separator + INCORRECT_FILES_FOLDER)
                .language(Language.PYTHON)
                .wrappersNames(new HashMap<>())
                .build();
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
    }

    private void initCorrectCaseClasses() {
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectPath(USER_DIRECTORY + File.separator + CORRECT_FILES_FOLDER)
                .language(Language.PYTHON)
                .wrappersNames(new HashMap<>())
                .build();
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
    }

    @Test
    public void compile_valid_solution_file(){
        initCorrectCaseClasses();
        PythonSolutionFilesCompiler pythonSolutionFilesCompiler = new PythonSolutionFilesCompiler();
        pythonSolutionFilesCompiler.compileSolutionFile();
        checkIsFileCompiled(CORRECT_FILES_FOLDER, SOLUTION_FILE_NAME);
        deleteCompiledFile(CORRECT_FILES_FOLDER, SOLUTION_FILE_NAME);
    }

    @Test
    public void compile_valid_test_solution_file(){
        initCorrectCaseClasses();
        PythonSolutionFilesCompiler pythonSolutionFilesCompiler = new PythonSolutionFilesCompiler();
        pythonSolutionFilesCompiler.compileTestSolutionFile();
        checkIsFileCompiled(CORRECT_FILES_FOLDER, TEST_SOLUTION_FILE_NAME);
        deleteCompiledFile(CORRECT_FILES_FOLDER, TEST_SOLUTION_FILE_NAME);
    }

    @Test
    public void execute_correct_solution_file(){
        initCorrectCaseClasses();
        PythonSolutionFilesCompiler pythonSolutionFilesCompiler = new PythonSolutionFilesCompiler();
        pythonSolutionFilesCompiler.compileSolutionFile();
        pythonSolutionFilesCompiler.executeSolutionFile();
        checkIsVisualizationStepsFileExists();
        deleteCompiledFile(CORRECT_FILES_FOLDER, SOLUTION_FILE_NAME);
        deleteVisualizationStepsFile();
    }

    @Test
    public void execute_incorrect_solution_file(){
        initIncorrectCaseClasses();
        PythonSolutionFilesCompiler pythonSolutionFilesCompiler = new PythonSolutionFilesCompiler();
        pythonSolutionFilesCompiler.compileSolutionFile();
        Assertions.assertThrows(SolutionFileExecutingException.class, pythonSolutionFilesCompiler::executeSolutionFile);
    }

    @Test
    public void execute_correct_test_solution_file(){
        initCorrectCaseClasses();
        PythonSolutionFilesCompiler pythonSolutionFilesCompiler = new PythonSolutionFilesCompiler();
        pythonSolutionFilesCompiler.compileTestSolutionFile();
        pythonSolutionFilesCompiler.executeTestSolutionFile();
        checkIsVisualizationStepsFileExists();
        deleteCompiledFile(CORRECT_FILES_FOLDER, TEST_SOLUTION_FILE_NAME);
        deleteVisualizationStepsFile();
    }

    @Test
    public void execute_incorrect_test_solution_file(){
        initIncorrectCaseClasses();
        PythonSolutionFilesCompiler pythonSolutionFilesCompiler = new PythonSolutionFilesCompiler();
        pythonSolutionFilesCompiler.compileTestSolutionFile();
        Assertions.assertThrows(SolutionFileExecutingException.class, pythonSolutionFilesCompiler::executeTestSolutionFile);
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

    private void checkIsVisualizationStepsFileExists() {
        String compiledClassPath = USER_DIRECTORY  + File.separator + CORRECT_FILES_FOLDER + File.separator + TARGET_DIRECTORY
                + File.separator + VISUALIZATION_STEPS_FILE_NAME;
        Assertions.assertTrue(new File(compiledClassPath).exists());
    }

    private void deleteVisualizationStepsFile() {
        try{
            new File(USER_DIRECTORY  + File.separator + CORRECT_FILES_FOLDER + File.separator + TARGET_DIRECTORY
                    + File.separator + VISUALIZATION_STEPS_FILE_NAME).delete();
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
