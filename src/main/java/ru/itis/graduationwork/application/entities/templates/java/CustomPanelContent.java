package ru.itis.graduationwork.application.entities.templates.java;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomPanelContent {

    private String packageValue;
    private List<String> imports;
    private String comment;
    private String parentClassName;

}
