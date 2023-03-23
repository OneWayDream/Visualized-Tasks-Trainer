package ru.itis.graduationwork.application.managers.project.visualization.registration.java;

import ru.itis.graduationwork.application.entities.project.VisualizationAction;
import ru.itis.graduationwork.application.managers.project.visualization.VisualizationActionsManager;

public class JavaVisualizationActionRegistrationManager {

    public static void registerAction(String nextStep, String previousStep, long delay){
        VisualizationActionsManager.addAction(
                new VisualizationAction(nextStep, previousStep, delay)
        );
    }

}
