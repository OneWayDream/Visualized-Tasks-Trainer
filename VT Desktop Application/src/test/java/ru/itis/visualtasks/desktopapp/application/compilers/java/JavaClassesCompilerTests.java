package ru.itis.visualtasks.desktopapp.application.compilers.java;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.exceptions.execution.JavaFileCompilationException;

import java.io.File;
import java.util.List;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.*;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class JavaClassesCompilerTests {
    
    private static final String FILES_FOLDER = "src\\test\\resources\\compilation\\java\\java_classes_compiler";
    private static final String FIRST_CORRECT_CLASS_FILE_NAME = "FirstCorrectClass";
    private static final String SECOND_CORRECT_CLASS_FILE_NAME = "SecondCorrectClass";
    private static final String FIRST_INCORRECT_CLASS_FILE_NAME = "FirstIncorrectClass";
    private static final String SECOND_INCORRECT_CLASS_FILE_NAME = "SecondIncorrectClass";


    @BeforeAll
    public static void beforeAll(){
        initProjectConfig();
    }

    private static void initProjectConfig() {
        ConfigManager.setConfig(ProjectConfig.builder()
                .projectPath(USER_DIRECTORY + File.separator + FILES_FOLDER)
                .build());
    }

    @ParameterizedTest
    @ValueSource(strings = {FIRST_CORRECT_CLASS_FILE_NAME, SECOND_CORRECT_CLASS_FILE_NAME})
    public void compile__correct_file(String fileToCompileName){
        String fileToCompileAbsolutePath = USER_DIRECTORY + File.separator + FILES_FOLDER + File.separator
                + fileToCompileName + JAVA_FILE_EXTENSION;
        JavaClassesCompiler.compileFile(fileToCompileAbsolutePath);
        checkIsFileCompiled(fileToCompileName);
    }

    @ParameterizedTest
    @ValueSource(strings = {FIRST_INCORRECT_CLASS_FILE_NAME, SECOND_INCORRECT_CLASS_FILE_NAME})
    public void compile_incorrect_file(String fileToCompileName){
        String fileToCompileAbsolutePath = USER_DIRECTORY + File.separator + FILES_FOLDER + File.separator
                + fileToCompileName + JAVA_FILE_EXTENSION;
        Assertions.assertThrows(JavaFileCompilationException.class, () -> JavaClassesCompiler.compileFile(fileToCompileAbsolutePath));
    }

    @Test
    public void compile_correct_files(){
        String firstFileToCompileAbsolutePath = USER_DIRECTORY + File.separator + FILES_FOLDER + File.separator
                + FIRST_CORRECT_CLASS_FILE_NAME + JAVA_FILE_EXTENSION;
        String secondFileToCompileAbsolutePath = USER_DIRECTORY + File.separator + FILES_FOLDER + File.separator
                + SECOND_CORRECT_CLASS_FILE_NAME + JAVA_FILE_EXTENSION;

        JavaClassesCompiler.compileFiles(List.of(new File(firstFileToCompileAbsolutePath),
                new File(secondFileToCompileAbsolutePath)));

        checkIsFileCompiled(FIRST_CORRECT_CLASS_FILE_NAME);
        checkIsFileCompiled(SECOND_CORRECT_CLASS_FILE_NAME);
    }

    private void checkIsFileCompiled(String fileName){
        String compiledClassPath = USER_DIRECTORY + File.separator + FILES_FOLDER + File.separator + TARGET_DIRECTORY
                + File.separator + fileName + JAVA_CLASS_FILE_EXTENSION;
        Assertions.assertTrue(new File(compiledClassPath).exists());
    }

    @Test
    public void compile_incorrect_files(){
        String firstFileToCompileAbsolutePath = USER_DIRECTORY + File.separator + FILES_FOLDER + File.separator
                + FIRST_CORRECT_CLASS_FILE_NAME + JAVA_FILE_EXTENSION;
        String secondFileToCompileAbsolutePath = USER_DIRECTORY + File.separator + FILES_FOLDER + File.separator
                + FIRST_INCORRECT_CLASS_FILE_NAME + JAVA_FILE_EXTENSION;
        Assertions.assertThrows(JavaFileCompilationException.class,
                () -> JavaClassesCompiler.compileFiles(List.of(
                        new File(firstFileToCompileAbsolutePath),
                        new File(secondFileToCompileAbsolutePath))
                ));
    }

    @AfterEach
    public void afterEach(){
        deleteCompiledFile(FIRST_CORRECT_CLASS_FILE_NAME);
        deleteCompiledFile(SECOND_CORRECT_CLASS_FILE_NAME);
    }

    @AfterAll
    public static void afterAll(){
        deleteCompiledFile(FIRST_CORRECT_CLASS_FILE_NAME);
        deleteCompiledFile(SECOND_CORRECT_CLASS_FILE_NAME);
        resetConfigManager();
    }

    private static void deleteCompiledFile(String compiledFileName) {
        try{
            new File(USER_DIRECTORY  + File.separator + FILES_FOLDER + File.separator + TARGET_DIRECTORY
                    + File.separator + compiledFileName + JAVA_CLASS_FILE_EXTENSION).delete();
        } catch (Exception exception){
            //ignore
        }
    }

    private static void resetConfigManager() {
        ConfigManager.setConfig(null);
    }

}
