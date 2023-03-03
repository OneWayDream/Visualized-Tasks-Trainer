package ru.itis.graduationwork.application.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecentListEntry {

    private String projectName;
    private long timeStamp;

}
