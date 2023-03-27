package ru.itis.visualtasks.desktopapp.application.entities.project;

import lombok.Data;

@Data
public class VisualizationAction {

    private String nextStepCommand;
    private String previousStepCommand;
    private long stepDelay;

    public VisualizationAction(String nextStepCommand, String previousStepCommand, long stepDelay){
        this.nextStepCommand = nextStepCommand;
        this.previousStepCommand = previousStepCommand;
        this.stepDelay = stepDelay;
    }

}
