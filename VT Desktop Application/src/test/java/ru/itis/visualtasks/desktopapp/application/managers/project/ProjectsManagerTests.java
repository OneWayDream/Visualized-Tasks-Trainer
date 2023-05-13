package ru.itis.visualtasks.desktopapp.application.managers.project;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.NewProjectInfo;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationType;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileDeletionException;

import java.io.File;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class ProjectsManagerTests {

    private static final String PROJECT_PATH = USER_DIRECTORY + File.separator
            + "src\\test\\resources\\managers\\project\\projects_manager";
    private static final String VISUALIZATION_FOLDER_PATH = PROJECT_PATH + File.separator + "visualization";
    private static final String WRAPPERS_FOLDER_PATH = PROJECT_PATH + File.separator + "wrappers";
    private static final String INFORMATIONAL_FOLDER_PATH = PROJECT_PATH + File.separator + "info";
    private static final String CUSTOM_PANEL_PATH = VISUALIZATION_FOLDER_PATH + File.separator + "CustomPanel.java";
    private static final String VISUALIZATION_SCENE_PANEL_PATH = VISUALIZATION_FOLDER_PATH + File.separator
            + "VisualizationScenePanel.java";
    private static final String CONFIG_PATH = PROJECT_PATH + File.separator + "config.json";

    @Test
    @Order(1)
    public void create_project(){
        ProjectsManager.createProject(NewProjectInfo.builder()
                        .projectName("Test project")
                        .projectPath(PROJECT_PATH)
                        .language(Language.JAVA)
                        .visualizationType(VisualizationType.SWING)
                        .build());
        Assertions.assertTrue(FilesManager.checkIsFileExist(PROJECT_PATH));
    }

    @Test
    @Order(2)
    public void open_project(){
        Application.main(null);
        ProjectsManager.openProject(PROJECT_PATH);
        Assertions.assertTrue(FilesManager.checkIsFileExist(CUSTOM_PANEL_PATH));
        Assertions.assertTrue(FilesManager.checkIsFileExist(VISUALIZATION_SCENE_PANEL_PATH));
        Assertions.assertTrue(FilesManager.checkIsFileExist(ProjectFilesManager.getSolutionFilePath()));
        Assertions.assertTrue(FilesManager.checkIsFileExist(ProjectFilesManager.getTestSolutionFilePath()));
        Assertions.assertTrue(FilesManager.checkIsFileExist(WRAPPERS_FOLDER_PATH));
        Assertions.assertTrue(FilesManager.checkIsFileExist(INFORMATIONAL_FOLDER_PATH));
    }

    @AfterAll
    public static void afterAll(){
        try{
            FilesManager.delete(PROJECT_PATH);
        } catch (FileDeletionException exception){
            //ignore
        }
        Application.getCurrentPageFrame().dispose();
    }

}
