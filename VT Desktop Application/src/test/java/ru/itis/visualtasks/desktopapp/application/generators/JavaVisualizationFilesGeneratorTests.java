package ru.itis.visualtasks.desktopapp.application.generators;

import org.junit.jupiter.api.*;
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
public class JavaVisualizationFilesGeneratorTests {

    private static final String PROJECT_PATH = USER_DIRECTORY + File.separator +
            "src\\test\\resources\\generation\\java_visualization_files_generator";
    private static final String VISUALIZATION_FOLDER = "visualization";
    private static final String CUSTOM_PANEL_FILE_NAME = "CustomPanel.java";
    private static final String VISUALIZATION_SCENE_PANEL_FILE_NAME = "VisualizationScenePanel.java";

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

    private void initSwingConfig(){
        ProjectConfig projectConfig = ConfigManager.getConfig();
        projectConfig.setVisualizationType(VisualizationType.SWING);
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
    }

    private void initJavaFXConfig(){
        ProjectConfig projectConfig = ConfigManager.getConfig();
        projectConfig.setVisualizationType(VisualizationType.JAVA_FX);
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
    }

    @Test
    public void generate_swing_custom_panel_file(){
        initSwingConfig();
        JavaVisualizationFilesGenerator.generateCustomPanelFile();
        String customPanelFilePath = getCustomPanelFilePath();
        Assertions.assertTrue(FilesManager.checkIsFileExist(customPanelFilePath));
        String codeFileContent = FilesManager.readFileAsString(customPanelFilePath);
        Assertions.assertTrue(codeFileContent.contains("public class CustomPanel extends JPanel"));
        deleteFile(customPanelFilePath);
    }

    @Test
    public void generate_java_fx_custom_panel_file(){
        initJavaFXConfig();
        JavaVisualizationFilesGenerator.generateCustomPanelFile();
        String customPanelFilePath = getCustomPanelFilePath();
        Assertions.assertTrue(FilesManager.checkIsFileExist(customPanelFilePath));
        String codeFileContent = FilesManager.readFileAsString(customPanelFilePath);
        Assertions.assertTrue(codeFileContent.contains("public class CustomPanel extends JFXPanel"));
        deleteFile(customPanelFilePath);
    }

    private String getCustomPanelFilePath(){
        return PROJECT_PATH + File.separator + VISUALIZATION_FOLDER + File.separator + CUSTOM_PANEL_FILE_NAME;
    }

    @Test
    public void generate_swing_visualization_scene_panel_file(){
        initSwingConfig();
        JavaVisualizationFilesGenerator.generateVisualizationScenePanelFile();
        String visualizationScenePanelFilePath = getVisualizationScenePanelFilePath();
        Assertions.assertTrue(FilesManager.checkIsFileExist(visualizationScenePanelFilePath));
        String codeFileContent = FilesManager.readFileAsString(visualizationScenePanelFilePath);
        Assertions.assertTrue(codeFileContent.contains("public class VisualizationScenePanel extends VisualizationSceneSwingPanelScheme"));
        deleteFile(visualizationScenePanelFilePath);
    }

    @Test
    public void generate_java_fx_visualization_scene_panel_file(){
        initJavaFXConfig();
        JavaVisualizationFilesGenerator.generateVisualizationScenePanelFile();
        String visualizationScenePanelFilePath = getVisualizationScenePanelFilePath();
        Assertions.assertTrue(FilesManager.checkIsFileExist(visualizationScenePanelFilePath));
        String codeFileContent = FilesManager.readFileAsString(visualizationScenePanelFilePath);
        Assertions.assertTrue(codeFileContent.contains("public class VisualizationScenePanel extends VisualizationSceneJavaFxPanelScheme"));
        deleteFile(visualizationScenePanelFilePath);
    }

    private String getVisualizationScenePanelFilePath(){
        return PROJECT_PATH + File.separator + VISUALIZATION_FOLDER + File.separator
                + VISUALIZATION_SCENE_PANEL_FILE_NAME;
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
