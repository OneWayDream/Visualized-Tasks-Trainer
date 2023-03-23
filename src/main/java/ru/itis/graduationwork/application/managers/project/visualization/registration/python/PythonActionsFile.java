package ru.itis.graduationwork.application.managers.project.visualization.registration.python;

import lombok.Data;
import ru.itis.graduationwork.application.entities.project.VisualizationAction;

import java.util.Map;

@Data
public class PythonActionsFile {

    private Map<Integer, VisualizationAction> actionMap;

}
