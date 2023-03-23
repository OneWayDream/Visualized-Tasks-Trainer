package ru.itis.graduationwork.application.entities.templates.readme;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WrappersReadMeContent {

    private String title;
    private String description;

}
