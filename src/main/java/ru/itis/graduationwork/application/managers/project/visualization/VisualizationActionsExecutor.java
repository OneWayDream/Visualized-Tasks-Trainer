package ru.itis.graduationwork.application.managers.project.visualization;

import ru.itis.graduationwork.application.ui.core.ide.visualization.core.VisualizationScenePanelScheme;
import ru.itis.graduationwork.exceptions.unexpected.UndefinedVisualizationMethodException;
import ru.itis.graduationwork.exceptions.project.VisualizationMethodInvocationException;

import java.lang.reflect.InvocationTargetException;

public class VisualizationActionsExecutor {

    private static VisualizationScenePanelScheme visualizationScenePanel;
    private static Class<?> panelClass;

    public static void setVisualizationScenePanel(VisualizationScenePanelScheme visualizationScenePanelScheme){
        visualizationScenePanel = visualizationScenePanelScheme;
        panelClass = visualizationScenePanelScheme.getClass();
    }

    public static void executeCommand(String command){
        try{
            panelClass.getMethod(command).invoke(visualizationScenePanel);
        } catch (InvocationTargetException | IllegalAccessException exception) {
            throw new VisualizationMethodInvocationException(exception);
        } catch (NoSuchMethodException exception) {
            throw new UndefinedVisualizationMethodException(exception);
        }
    }

}
