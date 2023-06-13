package ru.itis.visualtasks.desktopapp.application.managers.project;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationType;
import ru.itis.visualtasks.desktopapp.application.generators.FilesGenerator;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileDeletionException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class ProjectFilesManagerTests {

    private static final String PROJECT_PATH = USER_DIRECTORY + File.separator
            + "src\\test\\resources\\managers\\project\\project_files_manager";
    private static final String VISUALIZATION_FOLDER_PATH = PROJECT_PATH + File.separator + "visualization";
    private static final String WRAPPERS_FOLDER_PATH = PROJECT_PATH + File.separator + "wrappers";
    private static final String INFORMATIONAL_FOLDER_PATH = PROJECT_PATH + File.separator + "info";
    private static final String CUSTOM_PANEL_PATH = VISUALIZATION_FOLDER_PATH + File.separator + "CustomPanel.java";
    private static final String VISUALIZATION_SCENE_PANEL_PATH = VISUALIZATION_FOLDER_PATH + File.separator
            + "VisualizationScenePanel.java";
    private static final String CONFIG_PATH = PROJECT_PATH + File.separator + "config.json";
    private static FilesGenerator filesGenerator;

    private List<String> filesToDelete;

    @BeforeAll
    public static void beforeAll(){
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectName("Test project")
                .projectPath(PROJECT_PATH)
                .language(Language.JAVA)
                .visualizationType(VisualizationType.SWING)
                .build();
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
        LocalizationManager.setLocale(Locale.EN);
        filesGenerator = FilesGeneratorManager.getFilesGenerator(projectConfig.getLanguage());
    }

    @BeforeEach
    public void init(){
        filesToDelete = new ArrayList<>();
    }

    @Test
    @Order(1)
    public void create_project_directory(){
        ProjectFilesManager.createProjectFolders(ConfigManager.getProjectPath());
    }

    @Test
    @Order(2)
    public void get_solution_file_name(){
        Assertions.assertEquals(filesGenerator.getSolutionFileName(), ProjectFilesManager.getSolutionFileName());
    }

    @Test
    @Order(3)
    public void get_test_solution_file_name(){
        Assertions.assertEquals(filesGenerator.getTestSolutionFileName(), ProjectFilesManager.getTestSolutionFileName());
    }

    @Test
    @Order(4)
    public void create_visualization_files(){
        ProjectFilesManager.createVisualizationFiles();
        Assertions.assertTrue(FilesManager.checkIsFileExist(VISUALIZATION_FOLDER_PATH));
        Assertions.assertTrue(FilesManager.checkIsFileExist(CUSTOM_PANEL_PATH));
        Assertions.assertTrue(FilesManager.checkIsFileExist(VISUALIZATION_SCENE_PANEL_PATH));
    }

    @Test
    @Order(5)
    public void create_files(){
        ProjectFilesManager.createProjectFiles();
        Assertions.assertTrue(FilesManager.checkIsFileExist(VISUALIZATION_FOLDER_PATH));
        Assertions.assertTrue(FilesManager.checkIsFileExist(CUSTOM_PANEL_PATH));
        Assertions.assertTrue(FilesManager.checkIsFileExist(VISUALIZATION_SCENE_PANEL_PATH));
        Assertions.assertTrue(FilesManager.checkIsFileExist(ProjectFilesManager.getSolutionFilePath()));
        Assertions.assertTrue(FilesManager.checkIsFileExist(ProjectFilesManager.getTestSolutionFilePath()));
        Assertions.assertTrue(FilesManager.checkIsFileExist(WRAPPERS_FOLDER_PATH));
        Assertions.assertTrue(FilesManager.checkIsFileExist(INFORMATIONAL_FOLDER_PATH));
    }

    @Test
    @Order(6)
    public void get_config_file(){
        ProjectConfig config = ProjectFilesManager.getConfigFile(PROJECT_PATH);
        Assertions.assertNotNull(config);
        Assertions.assertEquals(PROJECT_PATH, config.getProjectName());
    }

    @Test
    @Order(7)
    public void write_config_file(){
        ProjectFilesManager.writeConfigFile(ConfigManager.getConfig());
        Assertions.assertTrue(FilesManager.checkIsFileExist(CONFIG_PATH));
    }

    @AfterAll
    public static void afterAll(){
        try{
            FilesManager.delete(PROJECT_PATH);
        } catch (FileDeletionException exception){
            //ignore
        }
        ProjectFilesManager.reset();
        ConfigManager.setConfig(null);
    }

}
