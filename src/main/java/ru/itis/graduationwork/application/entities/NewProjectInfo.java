package ru.itis.graduationwork.application.entities;

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
    private String language;

}
