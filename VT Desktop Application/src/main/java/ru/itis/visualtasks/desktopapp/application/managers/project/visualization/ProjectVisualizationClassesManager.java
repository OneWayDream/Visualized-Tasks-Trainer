package ru.itis.visualtasks.desktopapp.application.managers.project.visualization;

import javafx.embed.swing.JFXPanel;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationType;
import ru.itis.visualtasks.desktopapp.application.loaders.ProjectClassLoader;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.compilers.java.JavaClassesCompiler;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectTaskFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.VisualizationControlButtonsStatesManager;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.VisualizationSceneJavaFxPanelScheme;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.VisualizationScenePanelScheme;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.core.VisualizationSceneSwingPanelScheme;
import ru.itis.visualtasks.desktopapp.exceptions.execution.ProjectClassLoadingException;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnsupportedVisualizationTypeException;

import javax.swing.*;
import java.io.File;

public class ProjectVisualizationClassesManager {

    private static final String CUSTOM_PANEL_CLASS_NAME = "visualization.CustomPanel";
    private static final String VISUALIZATION_SCENE_PANEL_CLASS_NAME = "visualization.VisualizationScenePanel";
    private static final String JAVA_FILE_EXTENSION = Language.JAVA.getExtension();

    public static JComponent getVisualizationScene(){
        compileFilesIfNeeded();
        VisualizationScenePanelScheme visualizationScenePanelScheme = createVisualizationScene();
        updateVisualizationManagers(visualizationScenePanelScheme);
        return visualizationScenePanelScheme.getComponent();
    }

    private static void compileFilesIfNeeded(){
        if (Application.getMode() == Mode.DEVELOP){
            compileVisualizationFiles();
            ProjectTaskFilesManager.compileWrappersFiles();
        }
    }

    public static void compileVisualizationFiles(){
        JavaClassesCompiler.compileFile(fileNameToFilePath(CUSTOM_PANEL_CLASS_NAME));
        JavaClassesCompiler.compileFile(fileNameToFilePath(VISUALIZATION_SCENE_PANEL_CLASS_NAME));
    }

    private static String fileNameToFilePath(String className){
        return ConfigManager.getProjectPath() + File.separatorChar
                + className.replace('.', File.separatorChar) + JAVA_FILE_EXTENSION;
    }

    private static VisualizationScenePanelScheme createVisualizationScene(){
        VisualizationType visualizationType = ConfigManager.getProjectVisualizationType();
        VisualizationScenePanelScheme visualizationScenePanelScheme;
        switch (visualizationType){
            case SWING -> visualizationScenePanelScheme = getSwingVisualizationScene();
            case JAVA_FX -> visualizationScenePanelScheme = getJavaFxVisualizationScene();
            default -> throw new UnsupportedVisualizationTypeException();
        }
        return visualizationScenePanelScheme;
    }

    private static VisualizationScenePanelScheme getSwingVisualizationScene(){
        ProjectClassLoader classLoader = new ProjectClassLoader(ProjectVisualizationClassesManager.class.getClassLoader());
        try{
            Class<?> customPanelClass = Class.forName(CUSTOM_PANEL_CLASS_NAME, true,
                    classLoader);
            JPanel panel = (JPanel) customPanelClass.getDeclaredConstructor().newInstance();
            Class<?> visualizationScenePanelClass = Class.forName(VISUALIZATION_SCENE_PANEL_CLASS_NAME, true,
                    classLoader);
            return (VisualizationSceneSwingPanelScheme) visualizationScenePanelClass
                    .getDeclaredConstructor(JPanel.class).newInstance(panel);
        } catch (Exception exception) {
            throw new ProjectClassLoadingException(exception);
        }
    }

    private static VisualizationScenePanelScheme getJavaFxVisualizationScene(){
        ProjectClassLoader classLoader = new ProjectClassLoader(ProjectVisualizationClassesManager.class.getClassLoader());
        try{
            Class<?> customPanelClass = Class.forName(CUSTOM_PANEL_CLASS_NAME, true,
                    classLoader);
            JFXPanel panel = (JFXPanel) customPanelClass.getDeclaredConstructor().newInstance();
            Class<?> visualizationScenePanelClass = Class.forName(VISUALIZATION_SCENE_PANEL_CLASS_NAME, true,
                    classLoader);
            return (VisualizationSceneJavaFxPanelScheme) visualizationScenePanelClass
                    .getDeclaredConstructor(JFXPanel.class).newInstance(panel);
        } catch (Exception exception) {
            throw new ProjectClassLoadingException(exception);
        }
    }

    private static void updateVisualizationManagers(VisualizationScenePanelScheme visualizationScenePanelScheme){
        VisualizationActionsExecutor.setVisualizationScenePanel(visualizationScenePanelScheme);

        VisualizationControlButtonsStatesManager.setPlayPauseButtonsEnabledFlag(
                visualizationScenePanelScheme.isPlayPauseButtonsEnabled());
        VisualizationControlButtonsStatesManager.setStartEndButtonsEnabledFlag(
                visualizationScenePanelScheme.isStartEndButtonsEnabled());
        VisualizationControlButtonsStatesManager.setForwardBackStepButtonsEnabledFlag(
                visualizationScenePanelScheme.isForwardBackStepButtonsEnabled());

        VisualizationActionsManager.setInInitialStateCommand(visualizationScenePanelScheme.getInInitialStateCommand());
        VisualizationActionsManager.setInitialStepDelay(visualizationScenePanelScheme.getInitialStepDelay());
        VisualizationActionsManager.setAtSceneStartCommand(visualizationScenePanelScheme.getAtSceneStartCommand());
        VisualizationActionsManager.setAtStartStepDelay(visualizationScenePanelScheme.getAtStartStepDelay());
        VisualizationActionsManager.setAtSceneEndCommand(visualizationScenePanelScheme.getAtSceneEndCommand());
        VisualizationActionsManager.setAtEndStepDelay(visualizationScenePanelScheme.getAtEndStepDelay());
    }

}
