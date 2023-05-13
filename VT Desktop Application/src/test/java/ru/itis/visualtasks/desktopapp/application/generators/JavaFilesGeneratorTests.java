package ru.itis.visualtasks.desktopapp.application.generators;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationType;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;

import java.io.File;
import java.util.HashMap;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class JavaFilesGeneratorTests {

    private static final String PROJECT_PATH = USER_DIRECTORY + File.separator +
            "src\\test\\resources\\generation\\java_files_generator";
    private static final String SOLUTION_FILE_NAME = "Solution.java";
    private static final String TEST_SOLUTION_FILE_NAME = "TestSolution.java";

    private final JavaFilesGenerator javaFilesGenerator = new JavaFilesGenerator();

    @BeforeAll
    public static void beforeAll(){
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectPath(PROJECT_PATH)
                .language(Language.JAVA)
                .visualizationType(VisualizationType.SWING)
                .wrappersNames(new HashMap<>())
                .build();
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
        LocalizationManager.setLocale(Locale.EN);
    }

    @Test
    public void get_solution_file_name(){
        Assertions.assertEquals("Solution.java", javaFilesGenerator.getSolutionFileName());
    }

    @Test
    public void get_test_solution_file_name(){
        Assertions.assertEquals("TestSolution.java", javaFilesGenerator.getTestSolutionFileName());
    }

    @Test
    public void generate_additional_files(){
        // java don't generate extra files
        javaFilesGenerator.generateAdditionalFiles();
    }

    @ParameterizedTest
    @ValueSource(strings = "Test.java")
    public void generate_not_wrapper_code_file(String codeFileName){
        String codeFilePath = PROJECT_PATH + File.separator + codeFileName;
        javaFilesGenerator.generateCodeFile(codeFilePath);
        Assertions.assertTrue(FilesManager.checkIsFileExist(codeFilePath));
        String codeFileContent = FilesManager.readFileAsString(codeFilePath);
        Assertions.assertTrue(codeFileContent.contains("public class Test"));
        Assertions.assertFalse(codeFileContent.contains("import"));
        deleteFile(codeFilePath);
    }

    @ParameterizedTest
    @ValueSource(strings = "wrappers\\Test.java")
    public void generate_wrapper_code_file(String codeFileName){
        String codeFilePath = PROJECT_PATH + File.separator + codeFileName;
        javaFilesGenerator.generateCodeFile(codeFilePath);
        Assertions.assertTrue(FilesManager.checkIsFileExist(codeFilePath));
        String codeFileContent = FilesManager.readFileAsString(codeFilePath);
        Assertions.assertTrue(codeFileContent.contains("public class Test"));
        Assertions.assertTrue(codeFileContent.contains("import"));
        deleteFile(codeFilePath);
    }

    @Test
    public void generate_solution_file(){
        javaFilesGenerator.generateSolutionFile();
        String solutionFilePath = PROJECT_PATH + File.separator + SOLUTION_FILE_NAME;
        Assertions.assertTrue(FilesManager.checkIsFileExist(solutionFilePath));
        deleteFile(solutionFilePath);
    }

    @Test
    public void generate_test_solution_file(){
        javaFilesGenerator.generateTestSolutionFile();
        String testSolutionFilePath = PROJECT_PATH + File.separator + TEST_SOLUTION_FILE_NAME;
        Assertions.assertTrue(FilesManager.checkIsFileExist(testSolutionFilePath));
        deleteFile(testSolutionFilePath);
    }

    private void deleteFile(String filePath){
        try{
            new File(filePath).delete();
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
