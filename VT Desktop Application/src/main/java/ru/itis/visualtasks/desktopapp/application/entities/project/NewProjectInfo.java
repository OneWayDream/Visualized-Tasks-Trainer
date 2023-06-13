package ru.itis.visualtasks.desktopapp.application.entities.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewProjectInfo {

    private String projectName;
    private String projectPath;
    private Language language;
    private VisualizationType visualizationType;

}
