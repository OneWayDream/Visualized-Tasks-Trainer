package ru.itis.visualtasks.desktopapp.application.entities.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecentListEntry {

    private String projectName;
    private long timeStamp;

}
