package ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.core.TestPageFrame;
import ru.itis.visualtasks.desktopapp.application.managers.content.ImagesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationActionsManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationSceneController;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.chain.VisualizationControlButtonsDisableReasonManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.registration.java.JavaVisualizationActionRegistrationManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.buttons.VisualizationButtonType;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.components.VisualizationControlPanel;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.components.VisualizationPanel;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class VisualizationControlButtonsManagerTests {

    private static boolean isFileExecuted = false;

    @BeforeAll
    public static void beforeAll(){
        Application.changePage(new TestPageFrame());
        Application.changeMode(Mode.DEVELOP);
        ImagesManager.setTheme(Theme.DARK);
        LocalizationManager.setLocale(Locale.EN);
        VisualizationActionsManager.reset();
        VisualizationSceneController.setVisualizationPanel(new VisualizationPanel());
        VisualizationControlButtonsManager.reset();
        new VisualizationControlPanel();
    }

    @ParameterizedTest
    @Order(1)
    @EnumSource(VisualizationButtonType.class)
    public void get_default_disable_reason(VisualizationButtonType visualizationButtonType){
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                "ide.content.visualization-scene.control-buttons.not-executed-solution-file.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(visualizationButtonType)
        );
    }

    @ParameterizedTest
    @Order(2)
    @EnumSource(VisualizationButtonType.class)
    public void get_no_animation_steps_disable_reason(VisualizationButtonType visualizationButtonType){
        executeSolutionFile();
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.non-animation.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(visualizationButtonType)
        );
    }

    private void executeSolutionFile(){
        if (!isFileExecuted){
            VisualizationControlButtonsManager.notifyAboutFileExecution();
            isFileExecuted = true;
        }
    }

    @Test
    @Order(3)
    public void get_disable_reason_for_initial_state(){
        VisualizationControlButtonsManager.reset();
        new VisualizationControlPanel();
        JavaVisualizationActionRegistrationManager.registerAction("next", "previous", 0);
        VisualizationControlButtonsManager.notifyAboutFileExecution();
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.no-previous-action.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.AT_START)
        );
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.no-previous-action.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.PREVIOUS_STEP)
        );
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.not-playing-for-pause.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.PAUSE)
        );
    }

    @Test
    @Order(4)
    public void get_last_step_disable_reason(){
        VisualizationControlButtonsManager.notifyAboutTheLastVisualizationAction();
        VisualizationControlButtonsManager.notifyAboutNotTheFirstVisualizationAction();
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.no-next-action.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.PLAY)
        );
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.no-next-action.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.NEXT_STEP)
        );
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.no-next-action.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.AT_END)
        );
    }

    @Test
    @Order(5)
    public void get_previous_step_disable_reason(){
        VisualizationControlButtonsManager.notifyAboutNotTheLastVisualizationAction();
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.not-playing-for-pause.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.PAUSE)
        );
    }

    @Test
    @Order(6)
    public void get_first_step_disable_reason(){
        VisualizationControlButtonsManager.notifyAboutTheFirstVisualizationAction();
        VisualizationControlButtonsManager.notifyAboutNotTheLastVisualizationAction();
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.no-previous-action.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.AT_START)
        );
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.no-previous-action.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.PREVIOUS_STEP)
        );
    }

    @Test
    @Order(7)
    public void get_next_step_disable_reason(){
        VisualizationControlButtonsManager.notifyAboutNotTheFirstVisualizationAction();
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.not-playing-for-pause.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.PAUSE)
        );
    }

    @Test
    @Order(8)
    public void get_play_pause_disable_reason(){
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.not-playing-for-pause.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.PAUSE)
        );
        VisualizationControlButtonsManager.notifyAboutScenePlayingStart();
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.playing-scene.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.AT_START)
        );
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.playing-scene.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.PREVIOUS_STEP)
        );
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.playing-scene.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.PLAY)
        );
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.playing-scene.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.NEXT_STEP)
        );
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.playing-scene.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.AT_END)
        );
        VisualizationControlButtonsManager.notifyAboutScenePlayingFinish();
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.not-playing-for-pause.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.PAUSE)
        );
    }

    @Test
    @Order(9)
    public void change_locale(){
        VisualizationControlButtonsDisableReasonManager.changeLocale();
        Assertions.assertEquals(LocalizationManager.getLocaleTextByKey(
                        "ide.content.visualization-scene.control-buttons.not-playing-for-pause.tooltip-text"),
                VisualizationControlButtonsManager.getDisableReason(VisualizationButtonType.PAUSE)
        );
    }

}
