package ru.itis.visualtasks.desktopapp.application.entities.project;

import lombok.Getter;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnsupportedVisualizationTypeException;

import java.util.Arrays;

public enum VisualizationType {

    SWING("Swing"),
    JAVA_FX("JavaFX");

    @Getter
    private final String name;

    VisualizationType(String name){
        this.name = name;
    }

    public static String[] getVisualizationTypeNames(){
        return Arrays.stream(values())
                .map(VisualizationType::getName)
                .toList().toArray(new String[0]);
    }

    public static VisualizationType getByName(String name){
        return Arrays.stream(values())
                .filter(visualizationType -> visualizationType.getName().equals(name))
                .findFirst()
                .orElseThrow(UnsupportedVisualizationTypeException::new);
    }

}
