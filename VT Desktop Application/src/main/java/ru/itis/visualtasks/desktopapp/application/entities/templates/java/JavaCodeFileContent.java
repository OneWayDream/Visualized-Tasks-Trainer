package ru.itis.visualtasks.desktopapp.application.entities.templates.java;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JavaCodeFileContent {

    private String packageValue;
    private Boolean isWrapperClass;
    private List<String> imports;
    private String fileName;
    private String annotationComment;
    private String visualizationActionRegistrationComment;

}
