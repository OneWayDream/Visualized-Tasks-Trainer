package ru.itis.graduationwork.application.entities;

import lombok.Data;

@Data
public class VisualizationAction {

    private String command;
    private long timeToNext;

    public VisualizationAction(String command, long timeToNext){
        this.command = command;
        this.timeToNext = timeToNext;
    }

}
