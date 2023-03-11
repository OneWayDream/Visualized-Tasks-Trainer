package ru.itis.graduationwork.application.managers.project;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.entities.Language;
import ru.itis.graduationwork.application.loaders.ProjectClassLoader;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.settings.Mode;
import ru.itis.graduationwork.application.ui.core.ide.visualization.core.VisualizationScenePanelScheme;
import ru.itis.graduationwork.exceptions.ProjectClassLoadingException;

import javax.swing.*;
import java.io.File;

public class VisualizationProjectClassesManager {

    private static final String CUSTOM_PANEL_CLASS_NAME = "visualization.CustomPanel";
    private static final String VISUALIZATION_SCENE_PANEL_CLASS_NAME = "visualization.VisualizationScenePanel";
    private static final String JAVA_FILE_EXTENSION = Language.JAVA.getExtension();

    public static JComponent getVisualizationScene(){
        compileFilesIfNeeded();
        ProjectClassLoader classLoader = new ProjectClassLoader();
        try{
            Class<?> customPanelClass = Class.forName(CUSTOM_PANEL_CLASS_NAME, true,
                    classLoader);
            JPanel panel = (JPanel) customPanelClass.getDeclaredConstructor().newInstance();
            Class<?> visualizationScenePanelClass = Class.forName(VISUALIZATION_SCENE_PANEL_CLASS_NAME, true,
                    classLoader);
            VisualizationScenePanelScheme visualizationScenePanelScheme = (VisualizationScenePanelScheme)
                    visualizationScenePanelClass.getDeclaredConstructor(JPanel.class).newInstance(panel);
            VisualizationSceneController.setVisualizationScenePanel(visualizationScenePanelScheme);
            return visualizationScenePanelScheme.getComponent();
        } catch (Exception exception) {
            throw new ProjectClassLoadingException(exception);
        }
    }

    private static void compileFilesIfNeeded(){
        if (Application.getMode() == Mode.DEVELOP){
            compileVisualizationFiles();
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

}
