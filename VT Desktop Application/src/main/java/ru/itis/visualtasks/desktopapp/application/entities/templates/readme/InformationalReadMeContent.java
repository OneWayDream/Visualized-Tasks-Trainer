package ru.itis.visualtasks.desktopapp.application.entities.templates.readme;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InformationalReadMeContent {

    private String title;
    private String description;

}
