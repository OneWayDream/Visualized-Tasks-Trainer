package ru.itis.visualtasks.desktopapp.application.managers.project.visualization;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationAction;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class VisualizationActionsManagerTests {

    private final VisualizationAction FIRST_VISUALIZATION_ACTION = new VisualizationAction(
            "next", "previous", 0);
    private final VisualizationAction SECOND_VISUALIZATION_ACTION = new VisualizationAction(
            "new_next", "previous", 0);

    @BeforeAll
    public static void beforeAll(){
        VisualizationActionsManager.reset();
    }

    @Test
    @Order(1)
    public void init_manager(){
        Assertions.assertEquals(0, VisualizationActionsManager.getActionsAmount());
        Assertions.assertEquals(0, VisualizationActionsManager.getCurrentActionNumber());
        Assertions.assertFalse(VisualizationActionsManager.hasAnyActions());
        VisualizationActionsManager.setInInitialStateCommand("init");
        VisualizationActionsManager.setInitialStepDelay(1000);
        VisualizationActionsManager.setAtSceneStartCommand("start");
        VisualizationActionsManager.setAtStartStepDelay(1000);
        VisualizationActionsManager.setAtSceneEndCommand("finish");
        VisualizationActionsManager.setAtEndStepDelay(1000);
    }

    @Test
    @Order(2)
    public void add_visualization_action(){
        VisualizationActionsManager.addAction(FIRST_VISUALIZATION_ACTION);
        VisualizationActionsManager.addAction(SECOND_VISUALIZATION_ACTION);
    }

    @Test
    @Order(3)
    public void on_finish(){
        VisualizationActionsManager.onFinish();
        Assertions.assertEquals(1, VisualizationActionsManager.getCurrentActionNumber());
        Assertions.assertFalse(VisualizationActionsManager.hasNext());
    }

    @Test
    @Order(4)
    public void on_start(){
        VisualizationActionsManager.onStart();
        Assertions.assertEquals(0, VisualizationActionsManager.getCurrentActionNumber());
        Assertions.assertFalse(VisualizationActionsManager.hasPrevious());
    }

    @Test
    @Order(5)
    public void peek_next(){
        Assertions.assertEquals(SECOND_VISUALIZATION_ACTION, VisualizationActionsManager.peekNext());
    }

    @Test
    @Order(6)
    public void get_next(){
        Assertions.assertEquals(SECOND_VISUALIZATION_ACTION, VisualizationActionsManager.getNext());
    }

    @Test
    @Order(7)
    public void get_current(){
        Assertions.assertEquals(SECOND_VISUALIZATION_ACTION, VisualizationActionsManager.getCurrent());
    }

    @Test
    @Order(8)
    public void get_previous(){
        Assertions.assertEquals(FIRST_VISUALIZATION_ACTION, VisualizationActionsManager.getPrevious());
    }

    @AfterAll
    public static void afterAll(){
        VisualizationActionsManager.clear();
    }

}
