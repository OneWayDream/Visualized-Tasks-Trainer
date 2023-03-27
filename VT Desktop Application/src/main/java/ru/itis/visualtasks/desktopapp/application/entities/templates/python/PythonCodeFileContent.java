package ru.itis.visualtasks.desktopapp.application.entities.templates.python;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class PythonCodeFileContent {

    private Map<String, String> imports;
    private Boolean isWrapperScrypt;
    private String classAnnotationComment;
    private String functionAnnotationComment;
    private String visualizationActionRegistrationComment;

}
