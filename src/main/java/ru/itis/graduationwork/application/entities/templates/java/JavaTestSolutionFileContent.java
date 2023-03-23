package ru.itis.graduationwork.application.entities.templates.java;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JavaTestSolutionFileContent {

    private List<String> imports;
    private String comment;

}
