package ru.itis.visualtasks.desktopapp.application.entities.templates.java;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VisualizationSceneSwingPanelContent {

    private String packageValue;
    private List<String> imports;
    private String generalComment;
    private String buttonFlagsComment;

}
