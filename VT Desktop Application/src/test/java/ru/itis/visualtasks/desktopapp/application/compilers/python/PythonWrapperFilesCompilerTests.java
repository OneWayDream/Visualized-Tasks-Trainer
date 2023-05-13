package ru.itis.visualtasks.desktopapp.application.compilers.python;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;

import java.io.File;
import java.util.HashMap;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.TARGET_DIRECTORY;
import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class PythonWrapperFilesCompilerTests {

    private static final String NO_WRAPPERS_FILES_FOLDER = "src\\test\\resources\\compilation\\python\\python_wrapper_files_compiler\\no_wrappers";
    private static final String CORRECT_FILES_FOLDER = "src\\test\\resources\\compilation\\python\\python_wrapper_files_compiler\\correct";
    private static final String WRAPPERS_NAMES_FILE_NAME = "wrappers-names.json";

    private void initNoWrappersCaseClasses() {
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectPath(USER_DIRECTORY + File.separator + NO_WRAPPERS_FILES_FOLDER)
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
    public void compile_correct_wrapper_files(){
        initCorrectCaseClasses();
        new PythonWrapperFilesCompiler().compileWrappersFiles();
        // python wrapper files don't need to be compiled
    }

    @Test
    public void compile_incorrect_wrapper_files(){
        // python wrapper files don't need to be compiled
    }

    @Test
    public void get_void_wrappers_names(){
        initNoWrappersCaseClasses();
        PythonWrapperFilesCompiler pythonWrapperFilesCompiler = new PythonWrapperFilesCompiler();
        Assertions.assertTrue(pythonWrapperFilesCompiler.getWrappersNames().isEmpty());
        deleteWrappersNamesFile(NO_WRAPPERS_FILES_FOLDER);
    }

    @Test
    public void get_not_empty_wrappers_names(){
        initCorrectCaseClasses();
        PythonWrapperFilesCompiler pythonWrapperFilesCompiler = new PythonWrapperFilesCompiler();
        Assertions.assertEquals("Wrapper", pythonWrapperFilesCompiler.getWrappersNames().get("WrappedClass"));
        deleteWrappersNamesFile(CORRECT_FILES_FOLDER);
    }

    private void deleteWrappersNamesFile(String folder) {
        try{
            new File(USER_DIRECTORY  + File.separator + folder + File.separator + TARGET_DIRECTORY
                    + File.separator + WRAPPERS_NAMES_FILE_NAME).delete();
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
