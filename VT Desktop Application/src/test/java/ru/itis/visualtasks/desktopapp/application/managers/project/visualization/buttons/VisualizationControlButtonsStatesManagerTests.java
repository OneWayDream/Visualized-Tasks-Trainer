package ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.core.TestPageFrame;
import ru.itis.visualtasks.desktopapp.application.managers.content.ImagesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationSceneController;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.components.VisualizationPanel;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class VisualizationControlButtonsStatesManagerTests {

    @BeforeAll
    public static void beforeAll(){
        VisualizationControlButtonsStatesManager.reset();
        Application.changePage(new TestPageFrame());
        Application.changeMode(Mode.DEVELOP);
        ImagesManager.setTheme(Theme.DARK);
        LocalizationManager.setLocale(Locale.EN);
        VisualizationSceneController.setVisualizationPanel(new VisualizationPanel());
    }

    @Test
    @Order(1)
    public void disable_start_end_buttons(){
        Assertions.assertTrue(VisualizationControlButtonsStatesManager.isStartEndButtonsEnabled());
        VisualizationControlButtonsStatesManager.setStartEndButtonsEnabledFlag(false);
        Assertions.assertFalse(VisualizationControlButtonsStatesManager.isStartEndButtonsEnabled());
    }

    @Test
    @Order(2)
    public void disable_forward_back_step_buttons(){
        Assertions.assertTrue(VisualizationControlButtonsStatesManager.isForwardBackStepButtonsEnabled());
        VisualizationControlButtonsStatesManager.setForwardBackStepButtonsEnabledFlag(false);
        Assertions.assertFalse(VisualizationControlButtonsStatesManager.isForwardBackStepButtonsEnabled());
    }

    @Test
    @Order(3)
    public void disable_play_pause_buttons(){
        Assertions.assertTrue(VisualizationControlButtonsStatesManager.isPlayPauseButtonsEnabled());
        VisualizationControlButtonsStatesManager.setPlayPauseButtonsEnabledFlag(false);
        Assertions.assertFalse(VisualizationControlButtonsStatesManager.isPlayPauseButtonsEnabled());
    }

    @Test
    @Order(4)
    public void change_play_scene_flag(){
        Assertions.assertFalse(VisualizationControlButtonsStatesManager.isScenePlaying());
        VisualizationControlButtonsStatesManager.notifyAboutScenePlaying();
        Assertions.assertTrue(VisualizationControlButtonsStatesManager.isScenePlaying());
        VisualizationControlButtonsStatesManager.notifyAboutSceneStatic();
        Assertions.assertFalse(VisualizationControlButtonsStatesManager.isScenePlaying());
    }

    @Test
    @Order(5)
    public void change_solution_file_execution_flag(){
        Assertions.assertFalse(VisualizationControlButtonsStatesManager.isSolutionFileExecuted());
        VisualizationControlButtonsStatesManager.notifyAboutFileExecution();
        Assertions.assertTrue(VisualizationControlButtonsStatesManager.isSolutionFileExecuted());
    }

    @Test
    @Order(6)
    public void change_empty_actions_flag(){
        Assertions.assertTrue(VisualizationControlButtonsStatesManager.isAnyActions());
        VisualizationControlButtonsStatesManager.notifyAboutEmptyActions();
        Assertions.assertFalse(VisualizationControlButtonsStatesManager.isAnyActions());
    }

    @Test
    @Order(7)
    public void change_any_previous_actions_flag(){
        Assertions.assertFalse(VisualizationControlButtonsStatesManager.isAnyPreviousActions());
        VisualizationControlButtonsStatesManager.notifyAboutNotTheFirstVisualizationAction();
        Assertions.assertTrue(VisualizationControlButtonsStatesManager.isAnyPreviousActions());
        VisualizationControlButtonsStatesManager.notifyAboutTheFirstVisualizationAction();
        Assertions.assertFalse(VisualizationControlButtonsStatesManager.isAnyPreviousActions());
    }

    @Test
    @Order(8)
    public void change_any_next_actions_flag(){
        Assertions.assertFalse(VisualizationControlButtonsStatesManager.isAnyNextActions());
        VisualizationControlButtonsStatesManager.notifyAboutNotTheLastVisualizationAction();
        Assertions.assertTrue(VisualizationControlButtonsStatesManager.isAnyNextActions());
        VisualizationControlButtonsStatesManager.notifyAboutTheLastVisualizationAction();
        Assertions.assertFalse(VisualizationControlButtonsStatesManager.isAnyNextActions());
    }

    @AfterAll
    public static void afterAll(){
        VisualizationControlButtonsStatesManager.reset();
    }

}
