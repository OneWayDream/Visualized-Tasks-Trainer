package ru.itis.visualtasks.desktopapp.application.managers.project.visualization;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationType;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectTaskFilesManager;

import javax.swing.*;
import java.io.File;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class ProjectVisualizationClassesManagerTests {

    private static final String SWING_PROJECT_PATH = USER_DIRECTORY + File.separator
            + "src\\test\\resources\\managers\\project\\visualization\\project_visualization_classes_manager\\swing";
    private static final String JAVA_FX_PROJECT_PATH = USER_DIRECTORY + File.separator
            + "src\\test\\resources\\managers\\project\\visualization\\project_visualization_classes_manager\\java_fx";
    private static final String SWING_TARGET_FOLDER_PATH = SWING_PROJECT_PATH + File.separator + "target";
    private static final String JAVA_FX_TARGET_FOLDER_PATH = JAVA_FX_PROJECT_PATH + File.separator + "target";
    private static final String SWING_CUSTOM_PANEL_PATH = SWING_TARGET_FOLDER_PATH + File.separator
            +  "visualization\\CustomPanel.class";
    private static final String SWING_VISUALIZATION_SCENE_PANEL_PATH = SWING_TARGET_FOLDER_PATH + File.separator
            + "visualization\\VisualizationScenePanel.class";
    private static final String JAVA_FX_CUSTOM_PANEL_PATH = JAVA_FX_TARGET_FOLDER_PATH + File.separator
            +  "visualization\\CustomPanel.class";
    private static final String JAVA_FX_VISUALIZATION_SCENE_PANEL_PATH = JAVA_FX_TARGET_FOLDER_PATH + File.separator
            + "visualization\\VisualizationScenePanel.class";

    private void init_swing_project(){
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectName("Test project")
                .projectPath(SWING_PROJECT_PATH)
                .language(Language.JAVA)
                .visualizationType(VisualizationType.SWING)
                .build();
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
        ProjectTaskFilesManager.setProjectLanguage(Language.JAVA);
    }

    private void init_java_fx_project(){
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectName("Test project")
                .projectPath(JAVA_FX_PROJECT_PATH)
                .language(Language.JAVA)
                .visualizationType(VisualizationType.JAVA_FX)
                .build();
        ProjectFilesManager.init(projectConfig);
        ConfigManager.setConfig(projectConfig);
        ProjectTaskFilesManager.setProjectLanguage(Language.JAVA);
    }

    @Test
    public void get_swing_visualization_scene(){
        init_swing_project();
        ProjectVisualizationClassesManager.compileVisualizationFiles();
        JComponent visualizationPanel = ProjectVisualizationClassesManager.getVisualizationScene();
        Assertions.assertTrue(visualizationPanel instanceof JPanel);
        Assertions.assertTrue(FilesManager.checkIsFileExist(SWING_CUSTOM_PANEL_PATH));
        Assertions.assertTrue(FilesManager.checkIsFileExist(SWING_VISUALIZATION_SCENE_PANEL_PATH));
    }

    @Test
    public void get_java_fx_visualization_scene(){
        init_java_fx_project();
        ProjectVisualizationClassesManager.compileVisualizationFiles();
        JComponent visualizationPanel = ProjectVisualizationClassesManager.getVisualizationScene();
        Assertions.assertTrue(visualizationPanel instanceof JFXPanel);
        Assertions.assertTrue(FilesManager.checkIsFileExist(JAVA_FX_CUSTOM_PANEL_PATH));
        Assertions.assertTrue(FilesManager.checkIsFileExist(JAVA_FX_VISUALIZATION_SCENE_PANEL_PATH));
    }

    @AfterAll
    public static void afterAll(){
        ConfigManager.setConfig(null);
        ProjectFilesManager.reset();
        delete(SWING_TARGET_FOLDER_PATH);
        delete(JAVA_FX_TARGET_FOLDER_PATH);
    }

    private static void delete(String path){
        try {
            FilesManager.delete(path);
        } catch (Exception exception){
            //ignore
        }
    }

}
