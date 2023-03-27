package ru.itis.visualtasks.desktopapp.application.entities.templates.java;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JavaTestSolutionFileContent {

    private List<String> imports;
    private String comment;

}
