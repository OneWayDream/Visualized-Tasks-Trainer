package ru.itis.graduationwork.application.entities.templates.python;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class PythonSolutionFileContent {

    private Map<String, String> imports;
    private String comment;

}
