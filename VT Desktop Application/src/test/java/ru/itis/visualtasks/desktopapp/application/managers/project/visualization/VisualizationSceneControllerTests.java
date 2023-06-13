package ru.itis.visualtasks.desktopapp.application.managers.project.visualization;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.core.TestPageFrame;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationType;
import ru.itis.visualtasks.desktopapp.application.managers.content.ImagesManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectTaskFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.components.VisualizationPanel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class VisualizationSceneControllerTests {

    private static final String PROJECT_PATH = USER_DIRECTORY + File.separator
            + "src\\test\\resources\\managers\\project\\visualization\\visualization_scene_controller";

    private static final List<Integer> actionsRegistry = new ArrayList<>();

    public synchronized static void registryAction(Integer value){
        actionsRegistry.add(value);
    }

    public static void clearRegistry(){
        actionsRegistry.clear();
    }

    @BeforeAll
    public static void beforeAll(){
        ProjectConfig projectConfig = ProjectConfig.builder()
                .projectName("Test project")
                .projectPath(PROJECT_PATH)
                .visualizationType(VisualizationType.SWING)
                .language(Language.JAVA)
                .build();
        ConfigManager.setConfig(projectConfig);
        ProjectFilesManager.init(projectConfig);
        Application.changePage(new TestPageFrame());
        Application.changeMode(Mode.DEVELOP);
        ImagesManager.setTheme(Theme.DARK);
        LocalizationManager.setLocale(Locale.EN);
        VisualizationSceneController.setVisualizationPanel(new VisualizationPanel());
        ProjectTaskFilesManager.setProjectLanguage(Language.JAVA);
        ProjectTaskFilesManager.compileWrappersFiles();
        ProjectTaskFilesManager.compileSolutionFile();
        ProjectTaskFilesManager.executeSolutionFile();
    }

    @Test
    @Order(1)
    public void on_finish() throws InterruptedException {
        VisualizationSceneController.onFinish();
        Thread.sleep(300);
        Assertions.assertEquals(3, actionsRegistry.size());
        Assertions.assertEquals(1, actionsRegistry.get(0));
        Assertions.assertEquals(2, actionsRegistry.get(1));
        Assertions.assertEquals(3, actionsRegistry.get(2));
    }

    @Test
    @Order(2)
    public void on_start() throws InterruptedException {
        VisualizationSceneController.onStart();
        Thread.sleep(300);
        Assertions.assertEquals(0, actionsRegistry.size());
    }

    @Test
    @Order(3)
    public void next_step() throws InterruptedException {
        VisualizationSceneController.nextStep();
        Thread.sleep(300);
        Assertions.assertEquals(1, actionsRegistry.size());
        Assertions.assertEquals(1, actionsRegistry.get(0));
    }

    @Test
    @Order(4)
    public void previous_step() throws InterruptedException {
        VisualizationSceneController.previousStep();
        Thread.sleep(300);
        Assertions.assertEquals(0, actionsRegistry.size());
    }

    @Test
    @Order(5)
    public void test_pause_test() throws InterruptedException {
        VisualizationSceneController.play();
        Thread.sleep(500);
        VisualizationSceneController.pause();
        Assertions.assertEquals(2, actionsRegistry.size());
        Assertions.assertEquals(1, actionsRegistry.get(0));
        Assertions.assertEquals(2, actionsRegistry.get(1));
        VisualizationSceneController.play();
        Thread.sleep(300);
        Assertions.assertEquals(3, actionsRegistry.size());
    }

    @AfterEach
    public void afterEach(){
        clearRegistry();
    }

}
