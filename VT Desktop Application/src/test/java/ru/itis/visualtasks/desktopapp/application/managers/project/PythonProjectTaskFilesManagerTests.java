package ru.itis.visualtasks.desktopapp.application.managers.project;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.compilers.python.PythonSolutionFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.compilers.python.PythonWrapperFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.core.TestPageFrame;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationType;
import ru.itis.visualtasks.desktopapp.application.managers.content.ImagesManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationSceneController;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.components.VisualizationPanel;

import java.io.File;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class PythonProjectTaskFilesManagerTests {

    private static final String PROJECT_PATH = USER_DIRECTORY + File.separator
            + "src\\test\\resources\\managers\\project\\project_task_files_manager\\python";
    private static final String TARGET_DIRECTORY_PATH = PROJECT_PATH + File.separator + "target";
    private static final String VISUALIZATION_STEPS_FILE_PATH = TARGET_DIRECTORY_PATH + File.separator + "visualization-actions.json";

    @BeforeAll
    public static void beforeAll(){
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectName("Test project")
                .projectPath(PROJECT_PATH)
                .visualizationType(VisualizationType.SWING)
                .language(Language.PYTHON)
                .build();
        ConfigManager.setConfig(projectConfig);
        ProjectFilesManager.init(projectConfig);
        Application.changePage(new TestPageFrame());
        ImagesManager.setTheme(Theme.DARK);
        LocalizationManager.setLocale(Locale.EN);
        VisualizationSceneController.setVisualizationPanel(new VisualizationPanel());
        ProjectTaskFilesManager.setProjectLanguage(Language.PYTHON);
    }

    @Test
    public void get_compilers(){
        Assertions.assertEquals(PythonSolutionFilesCompiler.class,
                ProjectTaskFilesManager.getSolutionFilesCompiler().getClass());
        Assertions.assertEquals(PythonWrapperFilesCompiler.class,
                ProjectTaskFilesManager.getWrappersFilesCompiler().getClass());
    }

    @Test
    public void compile_wrappers_files(){
        ProjectTaskFilesManager.compileWrappersFiles();
        // python wrapper files don't need to be compiled
    }

    @Test
    public void compile_solution_file(){
        ProjectTaskFilesManager.compileSolutionFile();
        Assertions.assertTrue(FilesManager.checkIsFileExist(ProjectFilesManager.getSolutionFilePath()));
    }

    @Test
    public void compile_test_solution_file(){
        ProjectTaskFilesManager.compileTestSolutionFile();
        Assertions.assertTrue(FilesManager.checkIsFileExist(ProjectFilesManager.getTestSolutionFilePath()));
    }

    @Test
    public void execute_solution_file(){
        ProjectTaskFilesManager.executeSolutionFile();
        Assertions.assertTrue(FilesManager.checkIsFileExist(VISUALIZATION_STEPS_FILE_PATH));
    }

    @Test
    public void execute_test_solution_file(){
        ProjectTaskFilesManager.executeTestSolutionFile();
        Assertions.assertTrue(FilesManager.checkIsFileExist(VISUALIZATION_STEPS_FILE_PATH));
    }

    @AfterAll
    public static void afterAll(){
        try{
            FilesManager.delete(TARGET_DIRECTORY_PATH);
        } catch (Exception exception){
            //ignore
        }
        ConfigManager.setConfig(null);
        ProjectFilesManager.reset();
    }

}
