package ru.itis.visualtasks.desktopapp.application.managers.project.visualization;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.core.TestVisualizationScenePanelScheme;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class VisualizationActionsExecutorTests {

    public static boolean ACTION_CHECK_FLAG = false;

    @BeforeAll
    public static void beforeAll(){
        VisualizationActionsExecutor.setVisualizationScenePanel(new TestVisualizationScenePanelScheme());
    }

    @Test
    public void execute_method(){
        VisualizationActionsExecutor.executeCommand("makeAction");
        Assertions.assertTrue(ACTION_CHECK_FLAG);
    }

}
