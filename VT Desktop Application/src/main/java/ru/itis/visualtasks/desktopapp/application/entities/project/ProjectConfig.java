package ru.itis.visualtasks.desktopapp.application.entities.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectConfig {

    private String projectName;
    private String projectPath;
    private Language language;
    private VisualizationType visualizationType;
    private String generalDescriptionFilePath;
    private String studyingContentFilePath;
    private String taskTermsFilePath;
    private Map<String, String> wrappersNames;

}
