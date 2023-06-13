package ru.itis.visualtasks.desktopapp.application.generators;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;

import java.io.File;
import java.util.HashMap;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class FreeMarkerFilesGeneratorTests {

    private static final String PROJECT_PATH = USER_DIRECTORY + File.separator +
            "src\\test\\resources\\generation\\freemarker_files_generator";
    private static final String VISUALIZATION_FOLDER = "visualization";
    private static final String WRAPPERS_FOLDER = "wrappers";
    private static final String INFORMATIONAL_FOLDER = "info";
    private static final String READ_ME_FILE_NAME = "readme.md";
    private final FreeMarkerFilesGenerator freeMarkerFilesGenerator = new JavaFilesGenerator();

    @BeforeAll
    public static void beforeAll(){
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectPath(PROJECT_PATH)
                .language(Language.JAVA)
                .wrappersNames(new HashMap<>())
                .build();
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
        LocalizationManager.setLocale(Locale.EN);
    }

    @Test
    public void generate_visualization_read_me_file(){
        freeMarkerFilesGenerator.generateVisualizationReadMeFile();
        Assertions.assertTrue(FilesManager.checkIsFileExist(getVisualizationFolderPath()));
        Assertions.assertTrue(FilesManager.checkIsFileExist(getVisualizationReadMeFilePath()));
        deleteReadMe(VISUALIZATION_FOLDER);
    }

    private String getVisualizationFolderPath(){
        return PROJECT_PATH + File.separator + VISUALIZATION_FOLDER;
    }

    private String getVisualizationReadMeFilePath(){
        return getVisualizationFolderPath() + File.separator + READ_ME_FILE_NAME;
    }

    @Test
    public void generate_wrappers_read_me_file(){
        freeMarkerFilesGenerator.generateWrappersReadMeFile();
        Assertions.assertTrue(FilesManager.checkIsFileExist(getWrappersFolderPath()));
        Assertions.assertTrue(FilesManager.checkIsFileExist(getWrappersReadMeFilePath()));
        deleteReadMe(WRAPPERS_FOLDER);
    }

    private String getWrappersFolderPath(){
        return PROJECT_PATH + File.separator + WRAPPERS_FOLDER;
    }

    private String getWrappersReadMeFilePath() {
        return getWrappersFolderPath() + File.separator + READ_ME_FILE_NAME;
    }

    @Test
    public void generate_informational_read_me_file(){
        freeMarkerFilesGenerator.generateInformationalReadMeFile();
        Assertions.assertTrue(FilesManager.checkIsFileExist(getInformationalFolderPath()));
        Assertions.assertTrue(FilesManager.checkIsFileExist(getInformationalReadMeFilePath()));
        deleteReadMe(INFORMATIONAL_FOLDER);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test.java"})
    public void check_is_java_code_file(String fileName){
        Assertions.assertTrue(new JavaFilesGenerator().checkIsCodeFile(fileName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"scrypt.py.java"})
    public void check_is_python_code_file(String fileName){
        Assertions.assertTrue(new PythonFilesGenerator().checkIsCodeFile(fileName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"text.txt", "scrypt.js"})
    public void false_check_is_code_file(String fileName){
        Assertions.assertFalse(freeMarkerFilesGenerator.checkIsCodeFile(fileName));
    }

    private String getInformationalFolderPath(){
        return PROJECT_PATH + File.separator + INFORMATIONAL_FOLDER;
    }

    private String getInformationalReadMeFilePath() {
        return getInformationalFolderPath() + File.separator + READ_ME_FILE_NAME;
    }

    private void deleteReadMe(String folder){
        try{
            new File(PROJECT_PATH + File.separator + folder + File.separator + READ_ME_FILE_NAME).delete();
        } catch (Exception exception){
            //ignore
        }
    }

}
