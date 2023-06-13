package ru.itis.visualtasks.desktopapp.application.managers.project.visualization.registration.python;

import com.google.gson.Gson;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationAction;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationActionsManager;
import ru.itis.visualtasks.desktopapp.exceptions.project.ProjectConfigReadingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class PythonVisualizationActionRegistrationManager {

    private static final String ACTIONS_FILE_PATH = "target/visualization-actions.json";
    private static final Gson gson = new Gson();

    public static void notifyAboutSolutionExecution(){
        Map<Integer, VisualizationAction> actionMap = readActionsFile().getActionMap();
        for (Integer key: actionMap.keySet().stream().sorted().toList()){
            VisualizationAction visualizationAction = actionMap.get(key);
            VisualizationActionsManager.addAction(visualizationAction);
        }
    }

    private static PythonActionsFile readActionsFile(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getActionsFilePath(),
                StandardCharsets.UTF_8))){
            return gson.fromJson(bufferedReader, PythonActionsFile.class);
        } catch (IOException ex) {
            throw new ProjectConfigReadingException(ex);
        }
    }

    private static String getActionsFilePath(){
        return ConfigManager.getProjectPath() + File.separator + ACTIONS_FILE_PATH;
    }

}
