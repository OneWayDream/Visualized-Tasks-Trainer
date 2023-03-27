package ru.itis.visualtasks.desktopapp.application.entities.templates.python;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WrappersAnalysisContent {

    private List<String> modules;

}
